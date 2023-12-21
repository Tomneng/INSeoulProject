package com.inseoul.user.controller;

import com.inseoul.user.domain.ScrapQryResult;
import com.inseoul.user.service.UserScraptedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
public class ScraptController {

    @Autowired
    private UserScraptedService userScraptedService;

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

    //음식점
    //카드 리스트에서 스크랩, 상세로 가도 스크랩되어있게
    @GetMapping("/scraptedFood")
    public List scraptedFood(@RequestParam("user_id") Long userId){
        List<Long> foodList = userScraptedService.scraptedList(userId);
        return foodList;
    }

    //스크랩
    @PostMapping("/scraptFood")
    public ScrapQryResult scrapFood(
            @RequestParam("food_id") Long foodId,
            @RequestParam("user_id") Long userId
    ){
        return userScraptedService.scraptedFood(userId, foodId);
    }

}
