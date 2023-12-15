package com.inseoul.board.service;

import com.inseoul.board.domain.post.Post;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardService {

    int write(Post post);

    List<Post> list();

    Post selectById(Long postId);

    int update(Post post);

    int deleteById(Long postId);
}