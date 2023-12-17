package com.inseoul.user.controller;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.user.domain.ScrapQryResult;
import com.inseoul.user.domain.User;
import com.inseoul.real_estate.util.U;
import com.inseoul.user.service.UserScraptedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserMypageController {

    @Autowired
    private UserScraptedService userScraptedService;
    @GetMapping("/scraps")
    public void showScrapted(Model model) {
        User user = U.getLoggedUser();

    @RequestMapping("/mypage")
    public void mypage(Model model){
        userScraptedService.listById(model);
    }
}
