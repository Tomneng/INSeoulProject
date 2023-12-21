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
    public QryCommentList list(Long postId) {
        System.out.println("댓글서비스임플 list함수 postId값은 " + postId);
        QryCommentList list = new QryCommentList();

        List<Comment> comments = commentRepository.findByPost(postId);

        System.out.println("댓글--------------" + comments);

        list.setCount(comments.size());   // 댓글의 개수
        list.setList(comments);
        list.setStatus("OK");

        System.out.println(list);
        return list;
    }

    @Override
    public QryResult write(Long postId, Long userId, String content) {

        User user = userRepository.findById(userId);

        Comment comment = Comment.builder()
                .user(user)
                .content(content)
                .postId(postId)
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
