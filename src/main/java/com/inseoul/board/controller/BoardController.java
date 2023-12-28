package com.inseoul.board.controller;

import com.inseoul.board.domain.post.Post;
import com.inseoul.board.domain.post.PostValidator;
import com.inseoul.board.service.BoardService;
import com.inseoul.real_estate.util.U;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/board")   // board url로 넘어가기
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardController() {
        System.out.println("BoardController() 생성");
    }

    @GetMapping("/write")   //GetMapping 요청 들어온걸 받기
    public void write() {
    }

    @PostMapping("/write")  // PostMapping
    public String writeOk(
            @RequestParam Map<String, MultipartFile> files   // 첨부 파일
            , @Valid Post post
            , BindingResult result
            , Model model   // 매개변수 선언시 BindingResult 보다 Model 을 뒤에 두어야 한다.
            , RedirectAttributes redirectAttrs
    ) {

        System.out.println("보드컨트롤러 롸잇Ok의 post == " + post);

        // validation 에러가 있었다면 redirect 할거다!
        if (result.hasErrors()) {

            // addAttribute
            //    request parameters로 값을 전달.  redirect URL에 query string 으로 값이 담김
            //    request.getParameter에서 해당 값에 액세스 가능
            // addFlashAttribute
            //    일회성. 한번 사용하면 Redirect후 값이 소멸
            //    request parameters로 값을 전달하지않음
            //    '객체'로 값을 그대로 전달
            redirectAttrs.addFlashAttribute("postId", post.getPostId());
            redirectAttrs.addFlashAttribute("title", post.getTitle());
            redirectAttrs.addFlashAttribute("content", post.getContent());

            for (var err : result.getFieldErrors()) {
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/write";
        }

        System.out.println(post);
        model.addAttribute("result", boardService.write(post, files));
        System.out.println("보드컨트롤러 롸잇ok함수의 모델 = " + model);

        return "board/writeOk";
    }


    @GetMapping("/detail/{postId}")
    public String detail(@PathVariable(name = "postId") Long postId, Model model) { // key와 value로 이루어져있는 HashMapModel의 || addAttribute()를 통해 view에 전달할 데이터를 저장

//        Post post = boardService.selectById(postId);    // 보드서비스에 있는 셀렉트바이아이디라는 함스에다가 매개변수로 포스트아이디를 전달함
//        System.out.println("post = " + post);
//        System.out.println(post.getUser());

        System.out.println("보드컨트롤러 detail함수 postId = " + postId);
        System.out.println("보드컨트롤러 detail함수 모델(전) = " + model);

        model.addAttribute("post", boardService.detail(postId));   // key, value
        System.out.println("보드컨트롤러 detail함수 모델(후) = " + model);

        return "board/detail";
    }


    // 페이징있는 리스트화면
    @GetMapping("/list")
    public void list(Integer page, Model model) {
        System.out.println("페이지테스트"+page);
        boardService.list(page, model);
    }

    @PostMapping("/list")
    @ResponseBody
    public String filter(@RequestParam Map<String,String> mbti, Model model){
        System.out.println(mbti);
        boardService.list(mbti,model);
        return "redirect:board/list";
    }

    @GetMapping("/update/{postId}")
    public String update(@PathVariable Long postId, Model model) {
        Post post = boardService.selectById(postId);
        model.addAttribute("post", post);
        return "board/update";
    }

    @PostMapping("/update")
    public String updateOk(
            @RequestParam Map<String, MultipartFile> files  // 새로 추가될 첨부파일들
            , Long[] delfile    // 삭제될 파일들
            , @Valid Post post
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttrs
    ) {
        if (result.hasErrors()) {

            redirectAttrs.addFlashAttribute("title", post.getTitle());
            redirectAttrs.addFlashAttribute("content", post.getContent());

            for (var err : result.getFieldErrors()) {
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/update/" + post.getPostId();
        }
        model.addAttribute("result", boardService.update(post, files, delfile));
        return "board/updateOk";
    }


    @PostMapping("/delete")     // 이 주소로 접속하면 이 메소드를 호출한다.(매핑한다.)
    public String deleteOk(Long postId, Model model) {   // 이 함수 안에서 변수로 쓰고 있기 때문에 이름이바뀌어도 상관 없다.
//        System.out.println("왼쪽은 deleteById를 찍음" + postId);
        int result = boardService.deleteById(postId);   //boardService에 있는 deleteById 함수를 호출하는데 그 호출하는데 매개변수를 id값을 준것
//        System.out.println("야호" + result);  // 0이 나왔어
        model.addAttribute("result", result);
        return "board/deleteOk";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("initBinder() 호출");
        binder.setValidator(new PostValidator());
    }

    // 페이징
    // pageRows 변경시 동작
    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows) {
        System.out.println("역 있당" + page + pageRows);
        U.getSession().setAttribute("pageRows", pageRows);
        return "redirect:/board/list?page=" + page;
    }
}
