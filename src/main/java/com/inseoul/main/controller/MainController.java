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

    @RequestMapping("/map")
    public String testScroll() {
        return "redirect:/home/map";
    }

    @RequestMapping("/home/map")
    public void scroll(Model model) {
    }

}
