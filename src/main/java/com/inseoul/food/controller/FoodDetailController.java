package com.inseoul.food.controller;

import com.inseoul.food.domain.Review;
import com.inseoul.food.service.FoodService;
import com.inseoul.food.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/food")
public class FoodDetailController {

    @Autowired
    public FoodService foodService;
    @Autowired
    public ReviewService reviewService;

//    api 불러오기
//    @GetMapping("/food")
//    public void apiTest(@Valid Model model){
//        foodService.getapi(model);
//    }
//    음식점
    @GetMapping("/food_review/{foodId}")
    public String foodDetail(@PathVariable Long foodId, Model model){
        //음식점 정보
        model.addAttribute("foodrow", foodService.selectById(foodId));
        // 리뷰 카테고리
        model.addAttribute("reviewCategory", reviewService.showCategory(foodId));
        // 평점
        model.addAttribute("reviewAvg", foodService.showRating(foodId));

        return "food/food_review";
    }
    @PostMapping("/food_review")
    public String reviewDetail(Review review)
    {
        System.out.println(reviewService.countRw(review));

        String urld = "redirect:/food/food_review/" + review.getFoodId();
        return urld;
        }
    }

