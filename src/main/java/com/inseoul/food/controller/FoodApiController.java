package com.inseoul.food.controller;

import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.domain.Review;
import com.inseoul.food.service.FoodService;
import com.inseoul.food.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//restcontroller -> data를 response
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

    // 리뷰 여부 확인
    @PostMapping("/foodReview")
    public int reviewTest(
            @RequestParam("user_id") Long userId,
            @RequestParam("food_id") Long foodId){
        return reviewService.reviewdb(userId, foodId);
    }

//    @PostMapping("/foodInfo")
//    public FoodRow foodDetail(@RequestParam Long foodId){
//        FoodRow row =  foodService.selectById(foodId);
//        System.out.println("row" + row);
//        return row;
//    }
//
//    @PostMapping("/foodInfo2")
//    public String foodReview(@RequestParam Long foodId){
//        String review = reviewService.showCategory(foodId);
//        System.out.println("review" + review);
//        return review;
//    }

}