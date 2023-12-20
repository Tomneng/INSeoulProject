package com.inseoul.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class adminController {

    @GetMapping("/complains")
    public void showConmplain(){
     // 문의사항 리스팅
    }

    @GetMapping("/complain/{complainId}")
    public String detail(@PathVariable Long complainId, Model model){
//        model.addAttribute("row", houseService.findById(houseId)); 여기 어드민 버전으로 수정해야함
        return "admin/complain";
    }

}
