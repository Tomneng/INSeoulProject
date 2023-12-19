package com.inseoul.board.service;


import com.inseoul.board.domain.post.Comment;
import com.inseoul.board.domain.post.QryCommentList;
import com.inseoul.board.domain.post.QryResult;
import com.inseoul.board.repository.CommentRepository;
import com.inseoul.user.domain.User;
import com.inseoul.user.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(SqlSession sqlSession){
        commentRepository = sqlSession.getMapper(CommentRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);

        System.out.println("CommentService() 생성");
    }


    @Override
    public QryCommentList list(Long post_id) {
        QryCommentList list = new QryCommentList();

        List<Comment> comments = commentRepository.findByPost(post_id);

        list.setCount(comments.size());   // 댓글의 개수
        list.setList(comments);
        list.setStatus("OK");

        return list;
    }

    @Override
    public QryResult write(Long post_id, int userId, String content) {

        User user = userRepository.findById(post_id);

        Comment comment = Comment.builder()
                .user(user)
                .content(content)
                .post_id(post_id)
                .build();

        commentRepository.save(comment);

        QryResult result = QryResult.builder()
                .count(1)
                .status("OK")
                .build();


        return result;
    }

    @Override
    public QryResult delete(Long id) {
        int count = commentRepository.deleteById(id);
        String status = "FAIL";

        if(count > 0) status = "OK";

        QryResult result = QryResult.builder()
                .count(count)
                .status(status)
                .build();

        return result;
    }
}