package com.inseoul.board.service;

import com.inseoul.board.domain.post.Post;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BoardService {

    @Transactional  // 이 메소드는 '트랜잭션' 처리
    Post detail(Long id);

    List<Post> list();

    Post selectById(Long id);

    int deleteById(Long postId);

    // 페이징 리스트
    List<Post> list(Integer page, Model model);

    // 리스트 타입 별 필터
    List<Post> list(Map<String,String> mbti, Model model);

    @Transactional
    Post detail(long postId);
    int write(Post post, Map<String, MultipartFile> files);
    int update(Post post, Map<String, MultipartFile> files, Long[] delfile);
}
