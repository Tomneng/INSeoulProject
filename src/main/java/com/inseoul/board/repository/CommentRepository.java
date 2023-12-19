package com.inseoul.board.repository;


import com.inseoul.board.domain.post.Comment;

import java.util.List;

public interface CommentRepository {

    // 특정 글(post_id) 의 댓글 목록
    List<Comment> findByPost(Long post_id);

    // 댓글 작성 <-- Comment
    int save(Comment comment);

    // 특정 댓글 (id) 삭제
    int deleteById(Long id);
}