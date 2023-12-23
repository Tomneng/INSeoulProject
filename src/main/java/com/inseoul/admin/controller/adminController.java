package com.inseoul.admin.controller;

import com.inseoul.admin.domain.Complain;
import com.inseoul.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/complains")
    public void showConmplain(Integer page, Model model){
        adminService.list(page, model);
    }

    @GetMapping("/complain/{complainId}")
    public String detail(@PathVariable Long complainId, Model model){
        model.addAttribute("complain", adminService.findComById(complainId));
        return "admin/complain";
    }

    @PostMapping("/answered")
    public String answered(Complain complain){
        adminService.answered(complain);
        return "redirect:/admin/complains";
    }


}
