package com.inseoul.food.controller;

import com.inseoul.food.domain.FoodData;
import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.domain.Review;
import com.inseoul.food.service.FoodService;
import com.inseoul.food.service.ReviewService;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

//restcontroller
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
//        System.out.println("reviewService.write()");
//        model.addAttribute("review", reviewService.selectById(review.getReviewId()));
        model.addAttribute("review", reviewService.selectById(reviewId));
        return "food/food_review";
    }

    @PostMapping("/food_review")
    public String reviewDetail(
            @RequestParam("food_id") Long foodId,
            @RequestParam("user_id") Long userId,
            @RequestParam("review_star") Double reviewStar,
            @RequestParam(value = "review_category", required = false) String reviewCategory,
            @RequestParam("review_content") String reviewContent)
//            @RequestParam("review_id") Long reviewId)
    {
//        System.out.println("넘어오나?" + reviewStar);

        // 리뷰를 저장하는 로직
       reviewService.write(foodId, userId, reviewStar, reviewCategory, reviewContent);
//        System.out.println("저장되나" + reviewService.write(foodId, userId, reviewStar, reviewCategory, reviewContent));

        // 저장된 리뷰의 ID를 얻어옴
//         reviewService.getReviewId();
//            reviewService.selectById(reviewId);
        // 저장된 리뷰의 ID를 사용하여 리뷰를 조회하는 로직
//         reviewService.selectById(review.getReviewId());
//        System.out.println("출력되나?" + reviewService.selectById(reviewId));

        return "redirect:/food_review/" + foodId;
    }
}