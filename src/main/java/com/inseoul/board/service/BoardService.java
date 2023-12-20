package com.inseoul.board.service;

import com.inseoul.board.domain.post.Post;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

public interface BoardService {

    int write(Post post);

    List<Post> list();

    Post selectById(Long postId);

    int update(Post post);

    int deleteById(Long postId);


    // 페이징 리스트
    List<Post> list(Integer page, Model model);

    @Transactional
    Post detail(long postId);

}