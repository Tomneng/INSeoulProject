package com.inseoul.board.service;

import com.inseoul.board.domain.post.Post;
import com.inseoul.board.repository.PostRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private PostRepository postRepository;

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession) {    // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        postRepository = sqlSession.getMapper(PostRepository.class);
        System.out.println("BoardService() 생성");
    }

    @Override
    public int write(Post post) {
        System.out.println("라이트 함수 " + post);
        return postRepository.save(post);
    }   // 게시물(Post)을 저장하는 메서드를 나타냅니다.


    @Override
    public Post selectById(Long postId) {
        Post post = postRepository.findById(postId);
        System.out.println("셀렉트바이아이디함수 " + post);

        return post;
    }

    @Override
    public List<Post> list() {
        return postRepository.findAll();
    }


    @Override
    public int update(Post postId) {
        int result = postRepository.update(postId);
        return result;
    }

    @Override
    public int deleteById(Long postId) {
        int result = 0;
        Post post = postRepository.findById(postId);  // 존재하는 데이터인지 읽어와보기
        if(post != null){  // 존재한다면 삭제 진행.
            result = postRepository.deleteById(post);
        }
        return result;
    }
}