package com.inseoul.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/inseoul")
    public String home() {
        return "redirect:/home/main";
    }

    @RequestMapping("/home/main")
    public void common(Model model) {
    }

    @RequestMapping("/testscroll")
    public String testScroll() {
        return "redirect:/home/scroll";
    }

    @RequestMapping("/home/scroll")
    public void scroll(Model model) {
    }

}
