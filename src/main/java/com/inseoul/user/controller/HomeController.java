package com.inseoul.user.controller;

import com.inseoul.config.PrincipalDetails;
import com.inseoul.user.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "redirect:/home/main";
    }

    @RequestMapping("/home")
    public void home(Model model){}
    //-------------------------------------------------------------------------
    // 현재 로그인한 정보 Authentication 보기 (디버깅 등 용도로 활용)

    @RequestMapping("/auth")
    @ResponseBody
    public Authentication auth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // 매개변수에 Authentication 을 명시해도 주입된다 (인증한 이후)
    @RequestMapping("/auth2")
    @ResponseBody
    public Authentication auth2(Authentication authentication){
        return authentication;
    }

    @RequestMapping("/userDetails")
    @ResponseBody
    public PrincipalDetails userDetails(Authentication authentication){
        return (PrincipalDetails)authentication.getPrincipal();
    }

    // @AuthenticationPrincipal 을 사용하여 로그인한 사용자 정보 주입받을수 있다.
    // org.springframework.security.core.annotation.AuthenticationPrincipal
    @RequestMapping("/user")
    @ResponseBody
    public User username(@AuthenticationPrincipal PrincipalDetails userDetails){
        return (userDetails != null) ? userDetails.getUser() : null;
    }





}
