package com.inseoul.user.controller;

import com.inseoul.user.domain.ScrapQryResult;
import com.inseoul.user.domain.User;
import com.inseoul.real_estate.util.U;
import com.inseoul.user.service.UserScraptedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserMypageController {

    @Autowired
    private UserScraptedService userScraptedService;
    @GetMapping("/scraps")
    public void showScrapted(Model model) {
        User user = U.getLoggedUser();

        // 위 정보는 session 의 정보이므로 현재 로그인한 유저의 아이디를 기준으로 검색
        userScraptedService.listById(user.getUser(), model);
    }



}
