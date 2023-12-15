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
//        model.addAttribute("review", reviewService.write(review));
//        System.out.println("reviewService.write()");
//        model.addAttribute("review", reviewService.selectById(review.getReviewId()));
        return "food/food_review";
    }

    @PostMapping("/food_review/")
    public String reviewDetail(
//            @PathVariable Long foodId,
//            @PathVariable Long foodId,
//            @PathVariable Long foodId,
//            @PathVariable Long foodId,

            @RequestParam("food_id") Long foodId,
            @RequestParam("review_star") Double reviewStar,
            @RequestParam(value = "review_category", required = false) String reviewCategory,
            @RequestParam("review_content") String reviewContent){

        // 리뷰를 저장하는 로직
       reviewService.write(foodId, reviewStar, reviewCategory, reviewContent);
        System.out.println("저장되나" + reviewService.write(foodId, reviewStar, reviewCategory, reviewContent));

        // 저장된 리뷰의 ID를 얻어옴
        // reviewService.getReviewId();

        // 저장된 리뷰의 ID를 사용하여 리뷰를 조회하는 로직 (가정: reviewService.selectById 메소드가 특정 ID에 해당하는 리뷰를 조회하고 반환한다고 가정)
//        reviewService.selectById(review.getReviewId());
//        System.out.println("출력되나?" + reviewService.selectById(review.getReviewId()));

        return "redirect:/food_review/{foodId}";
    }
    //이미지 첨부파일
}