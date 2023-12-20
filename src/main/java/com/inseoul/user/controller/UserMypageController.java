package com.inseoul.user.controller;

import com.inseoul.user.domain.ScrapQryResult;
import com.inseoul.user.domain.User;
import com.inseoul.real_estate.util.U;
import com.inseoul.user.service.UserScraptedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserMypageController {

    @Autowired
    private UserScraptedService userScraptedService;

    @RequestMapping ("/mypage")
    public void mypage(Model model){
        userScraptedService.listById(model);
        userScraptedService.listByIdTour(model);
    }


}
