package com.inseoul.food.controller;

import com.inseoul.food.domain.FoodData;
import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.domain.Review;
import com.inseoul.food.service.FoodService;
import com.inseoul.food.service.ReviewService;
import com.inseoul.real_estate.domain.Row;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/food")
public class FoodApiController {

    @Autowired
    public FoodService foodService;
    @Autowired
    public ReviewService reviewService;

//    카드섹션?
    @GetMapping("/food")
    public void apiTest(@Valid Model model){
//        foodService.list(row, model);
        foodService.getapi(model);
    }
//    음식점
    @GetMapping("/food_review/{foodId}")
    public String foodDetail(@PathVariable Long foodId, Long reviewId, Review review, Model model){
        model.addAttribute("foodrow", foodService.selectById(foodId));
//        model.addAttribute("review", reviewService.selectById(reviewId));
        model.addAttribute("review", reviewService.write(review));
        System.out.println("reviewService.write()");
        model.addAttribute("review", reviewService.selectById(review.getReviewId()));
        return "food/food_review";
    }


    //이미지 첨부파일
}