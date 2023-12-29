package com.inseoul.user.controller;

import com.inseoul.real_estate.util.U;
import com.inseoul.user.domain.ScrapQryResult;
import com.inseoul.user.domain.User;
import com.inseoul.user.domain.User;
import com.inseoul.user.service.UserScraptedService;
import com.inseoul.user.service.UserService;
import com.inseoul.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
public class ScraptController {

    @Autowired
    private UserScraptedService userScraptedService;

    @Autowired
    private UserService userService;

    @GetMapping("/scrapted")
    public List scraptedHouse(
            @RequestParam("user_id") Long userId
    ){
        List<Long> list = userScraptedService.scraptedList(userId);
        return list;
    }

    @PostMapping("/scrapt")
    public ScrapQryResult scrapHouse(
            @RequestParam("house_id") Long houseId,
            @RequestParam("user_id") Long userId
    ){
        return userScraptedService.scrapted(userId, houseId);
    }

    @PostMapping("/chpass")
    public void changePassword(
            @RequestParam("password") String password,
            @RequestParam("userId") Long userId
    ){
        User user = new User();
        user.setPassword(password);
        user.setUserId(userId);
        userService.chPassword(user);
    }

    @GetMapping("/scraptedTour")
    public List scraptedTour(
            @RequestParam("user_id") Long userId
    ){
        List<Long> tourList = userScraptedService.scraptedTourList(userId);
        return tourList;
    }

    @PostMapping("/scraptTour")
    public ScrapQryResult scrapTour(
            @RequestParam("tour_id") Long tourId,
            @RequestParam("user_id") Long userId
    ){
        return userScraptedService.scraptedTour(userId, tourId);
    }
}
