package com.inseoul.food.controller;

import com.inseoul.food.domain.Review;
import com.inseoul.food.service.FoodService;
import com.inseoul.food.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
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
    //리뷰 작성
    @PostMapping("/food_review")
    public int reviewDetail(
            @RequestParam("food_id") Long foodId,
            @RequestParam("user_id") Long userId,
            @RequestParam("review_star") Double reviewStar,
            @RequestParam("review_category") String reviewCategory,
            @RequestParam("review_content") String reviewContent
    ) {
        Review review = new Review();
        review.setFoodId(foodId);
        review.setReviewContent(reviewContent);
        review.setReviewCategory(reviewCategory);
        review.setUserId(userId);
        review.setReviewStar(reviewStar);
        System.out.println(reviewService.countRw(review));
        return 1;
    }
}

