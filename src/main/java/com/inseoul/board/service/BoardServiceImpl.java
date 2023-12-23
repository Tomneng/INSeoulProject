package com.inseoul.board.service;

import com.inseoul.board.domain.post.Attachment;
import com.inseoul.board.domain.post.Post;
import com.inseoul.board.repository.AttachmentRepository;
import com.inseoul.board.repository.PostRepository;
import com.inseoul.real_estate.util.U;
import com.inseoul.user.domain.User;
import com.inseoul.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Value("${app.upload.path}")
    private String uploadDir;

    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;

    private PostRepository postRepository;

    private UserRepository userRepository;  // 생성자 만들어서

    private AttachmentRepository attachmentRepository;  // 첨부파일 생성자를 만들자

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession) {    // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        postRepository = sqlSession.getMapper(PostRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);    // 생성자에 대입했는데 세션에 있는 getMapper가 UserRepository에 있는 클래스를 가져외서 userRepository
        attachmentRepository = sqlSession.getMapper(AttachmentRepository.class);
        System.out.println("BoardService() 생성");
    }

    @Override
    public int write(Post post, Map<String, MultipartFile> files) {
        // 현재 로그인한 작성자 정보.
        User user = U.getLoggedUser();

        // 위 정보는 session 의 정보이고, 일단 DB 에서 다시 읽어온다
        user = userRepository.findById(user.getUserId());
        System.out.println("user = userRepository.findById(user.getId()); = " + user);

        post.setUser(user);   // 글 작성자 세팅

        int cnt = postRepository.save(post);

        // 첨부파일 추가
        addFiles(files, post.getPostId());

        return cnt;
    }

    // 특정 글(id) 첨부파일(들) 추가
    private void addFiles(Map<String, MultipartFile> files, Long postId) {
        if(files != null){
            for(var e : files.entrySet()){

                // name="upfile##" 인 경우만 첨부파일 등록. (이유, 다른 웹에디터와 섞이지 않도록..ex: summernote)
                if(!e.getKey().startsWith("upfile")) continue;

                // 첨부 파일 정보 출력
                System.out.println("\n첨부파일 정보: " + e.getKey());   // name값
                U.printFileInfo(e.getValue());   // 파일 정보 출력
                System.out.println();

                // 물리적인 파일 저장
                Attachment file = upload(e.getValue());
                System.out.println("추가된 첨부파일" + file);

                // 성공하면 DB 에도 저장
                if(file != null){
                    file.setPostId(postId);   // FK 설정
                    attachmentRepository.save(file);   // INSERT
                }
            }
        }
    } // end addFiles()

    // 물리적으로 파일 저장.  중복된 이름 rename 처리
    private Attachment upload(MultipartFile multipartFile) {
        Attachment attachment = null;

        // 담긴 파일이 없으면 pass
        String originalFilename = multipartFile.getOriginalFilename();
        if(originalFilename == null || originalFilename.length() == 0) return null;

        // 원본파일명
        String sourceName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        // 저장될 파일명
        String fileName = sourceName;

        // 파일명 이 중복되는지 확인
        File file = new File(uploadDir, sourceName);
        if(file.exists()){  // 이미 존재하는 파일명,  중복되면 다름 이름으로 변경하여 저장
            // a.txt => a_2378142783946.txt  : time stamp 값을 활용할거다!
            int pos = fileName.lastIndexOf(".");
            if(pos > -1){   // 확장자가 있는 경우
                String name = fileName.substring(0, pos);  // 파일 '이름'
                String ext = fileName.substring(pos + 1);   // 파일 '확장자'

                // 중복방지를 위한 새로운 이름 (현재시간 ms) 를 파일명에 추가
                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
            } else {  // 확장자가 없는 경우
                fileName += "_" + System.currentTimeMillis();
            }
        }
        // 저장할 파일명
        System.out.println("fileName: " + fileName);

        // java.nio
        Path copyOfLocation = Paths.get(new File(uploadDir, fileName).getAbsolutePath());
        System.out.println(copyOfLocation);

        try {
            // inputStream을 가져와서
            // copyOfLocation (저장위치)로 파일을 쓴다.
            // copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다

            Files.copy(
                    multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING    // 기존에 존재하면 덮어쓰기
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        attachment = Attachment.builder()
                .filename(fileName)   // 저장된 이름
                .sourcename(sourceName)  // 원본 이름
                .build();

        return attachment;
    }

    @Override
    @Transactional  // 이 메소드는 '트랜잭션' 처리
    public Post detail(Long id) {
        System.out.println("보드서비스임플 디테일 id값 == " + id); // 게시글번호 출력ok
        postRepository.incViewCnt(id);
        Post post = postRepository.findById(id);

        if(post != null){
            // 첨부파일(들) 정보 가져오기
            List<Attachment> fileList = attachmentRepository.findByPost(post.getPostId());
            setImage(fileList);   // 이미지 파일 여부 세팅
            post.setFileList(fileList);
        }

        return post;
    }

    // 이미지 파일 여부 세팅
    private void setImage(List<Attachment> fileList) {
        // upload 실제 물리적인 경로
        String realPath = new File(uploadDir).getAbsolutePath();

        for(var attachment : fileList){
            BufferedImage imgData = null;
            File f = new File(realPath, attachment.getFilename());  // 저장된 첨부파일에 대한 File 객체

            try {
                imgData = ImageIO.read(f);
            } catch (IOException e) {
                System.out.println("파일존재안함: " + f.getAbsolutePath() + "[" + e.getMessage() + "]");
                throw new RuntimeException(e);
            }

            if(imgData != null) attachment.setImage(true);  // 이미지 여부 체크!
        }
    }

    @Override
    public List<Post> list() {
        return postRepository.findAll();
    }

    // 페이징 리스트
    @Override
    public List<Post> list(Integer page, Model model) {
        // 현재 페이지 parameter
        if(page == null) page = 1;  // 디폴트는 1page
        if(page < 1) page = 1;

        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        long cnt = postRepository.countAll();   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);   // 총 몇 '페이지' ?

        // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지'
        int startPage = 0;
        int endPage = 0;

        // 해당 페이지의 글 목록
        List<Post> list = null;

        if(cnt > 0){  // 데이터가 최소 1개 이상 있는 경우만 페이징
            //  page 값 보정
            if(page > totalPage) page = totalPage;

            // 몇번째 데이터부터 fromRow
            int fromRow = (page - 1) * pageRows;

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            // 해당페이지의 글 목록 읽어오기
            list = postRepository.selectFromRow(fromRow, pageRows);
            System.out.println("보드서비스임플 페이징리스트함수 list값 == "+ list);

            model.addAttribute("list", list);
        } else {
            page = 0;
        }

        model.addAttribute("cnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        return list;
    }


    @Override
    public Post selectById(Long id) {
        Post post = postRepository.findById(id);

        if(post != null){
            // 첨부파일 정보 가져오기
            List<Attachment> fileList = attachmentRepository.findByPost(post.getPostId());
            setImage(fileList);   // 이미지 파일 여부 세팅
            post.setFileList(fileList);
        }
        return post;
    }

    @Override
    public int update(Post post, Map<String, MultipartFile> files, Long[] delfile) {
        int result = postRepository.update(post);

        // 새로운 첨부파일 추가
        addFiles(files, post.getPostId());

        // 삭제할 첨부파일(들) 삭제
        if(delfile != null){
            for(Long fileId : delfile){
                Attachment file = attachmentRepository.findById(fileId);
                if(file != null){
                    delFile(file);   // 물리적으로 파일 삭제
                    attachmentRepository.delete(file);   // DB 에서 삭제
                }
            }
        }
        return result;
    }

    // 특정 첨부파일9id) 를 물리적으로 삭제
    private void delFile(Attachment file) {
        String saveDirectory = new File(uploadDir).getAbsolutePath();
        File f = new File(saveDirectory, file.getFilename());  // 물리적으로 저장된 파일들이 삭제 대상
        System.out.println("삭제시도--> " + f.getAbsolutePath());

        if(f.exists()){
            if(f.delete()){
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }

    @Override
    public int deleteById(Long id) {
        int result = 0;
        Post post = postRepository.findById(id);  // 존재하는 데이터인지 읽어와보기
        if(post != null){  // 존재한다면 삭제 진행.
            // 물리적으로 저장된 첨부파일(들) 삭제
            List<Attachment> fileList = attachmentRepository.findByPost(id);
            if(fileList != null){
                for(Attachment file : fileList){
                    delFile(file);
                }
            }
            result = postRepository.delete(post);
        }
        System.out.println(post);
        return result;
    }


    @Override
    @Transactional
    public Post detail(long postId) {
        System.out.println("보드서비스임플 detail함수 실행");

        Post post = postRepository.findById(postId);

        return post;
    }


}
