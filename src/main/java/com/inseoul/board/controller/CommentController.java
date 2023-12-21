package com.inseoul.board.controller;

import com.inseoul.board.domain.post.QryCommentList;
import com.inseoul.board.domain.post.QryResult;
import com.inseoul.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController   // data 를 response 한다 ('View' 를 리턴하는게 아니다!)
// @Controller + @ResponseBody 와 같다.
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public QryCommentList list(Long postId) {
        System.out.println("여기 null(12/20 21:50 신철희) 댓글콘트롤러 list함수 postId값은 " + postId); // 여기 null(12/20 21:50 신철희)

        System.out.println("댓글컨트롤러 list함수의 commentService.list(postId) = " + commentService.list(postId));
        return commentService.list(postId);
    }

    @PostMapping("/write")
    public QryResult write(
            @RequestParam("post_id") Long postId,
            @RequestParam("user_id") Long userId,
            String content
    ) {
        return commentService.write(postId, userId, content);
    }


    @PostMapping("/delete")
    public QryResult delete(Long id) {
        return commentService.delete(id);
    }

}