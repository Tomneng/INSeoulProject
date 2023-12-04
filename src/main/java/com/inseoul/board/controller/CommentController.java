//package com.inseoul.board.controller;
//
//
//import com.lec.spring.domain.QryCommentList;
//import com.lec.spring.domain.QryResult;
//import com.lec.spring.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController   // data 를 response 한다 ('View' 를 리턴하는게 아니다!)
//// @Controller + @ResponseBody 와 같다.
//@RequestMapping("/comment")
//public class CommentController {
//
////    @Autowired
////    private CommentService commentService;
//
//    @GetMapping("/list")
//    public QryCommentList list(Long id) {
//        System.out.println(commentService.list(id));
//        return commentService.list(id);
//    }
//
//    @PostMapping("/write")
//    public QryResult write(
//            @RequestParam("post_id") Long postId,
//            @RequestParam("user_id")Long userId,
//            String content
//    ){
//        return commentService.write(postId, userId, content);
//    }
//
//
//    @PostMapping("/delete")
//    public QryResult delete(Long id) {
//        return commentService.delete(id);
//    }
//
//}