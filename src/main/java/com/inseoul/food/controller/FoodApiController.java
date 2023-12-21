package com.inseoul.food.controller;

import com.inseoul.food.domain.Review;
import com.inseoul.food.service.FoodService;
import com.inseoul.food.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//restcontroller
@RestController
@RequestMapping("/food")
public class FoodApiController {

    @Autowired
    public FoodService foodService;
    @Autowired
    public ReviewService reviewService;

//    api 불러오기
    @GetMapping("/food")
    public void apiTest(@Valid Model model){
        foodService.getapi(model);
    }


    @PostMapping("/foodReview")
    public int reviewTest(
            @RequestParam("user_id") Long userId,
            @RequestParam("food_id")Long foodId){
        return reviewService.reviewdb(userId, foodId);
    }

}