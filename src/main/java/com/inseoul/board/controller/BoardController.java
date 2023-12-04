package com.inseoul.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")   // board url로 넘어가기
public class BoardController {

    public BoardController(){
        System.out.println("BoardController() 생성");
    }

    @GetMapping("/write")   //GetMapping 요청 들어온걸 받기
    public void write(){}

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        System.out.println(id);
//        System.out.println(boardService.detail(id));
//
//        model.addAttribute("post", boardService.detail(id));
        return "board/detail";
    }

    @GetMapping("/list")
    public void list(Integer page, Model model){
//        List<Post> list = boardService.list();
//        model.addAttribute("list", list);
//        boardService.list(page, model);
    }

    @GetMapping("/update")
    public String update(/*@PathVari/{id}able Long id, */Model model){
//        Post post = boardService.selectById(id);
//        model.addAttribute("post", post);
        return "board/update";
    }



}
