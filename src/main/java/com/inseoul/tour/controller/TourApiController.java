package com.inseoul.tour.controller;

import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.service.FoodService;
import com.inseoul.food.service.ReviewService;
import com.inseoul.tour.domain.Item;
import com.inseoul.tour.service.TourApiService;
import com.inseoul.tour.service.TourService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apisave")
public class TourApiController {

    @Autowired
    public TourApiService tourApiService;

    @Autowired
    public TourService tourService;

    @Autowired
    public FoodService foodService;

    @Autowired
    public ReviewService reviewService;

    @GetMapping("/apisave")
    @ResponseBody
    public void getTourApi(Item item2) {
        tourApiService.getTourApi(item2);
    }

    @PostMapping("/getThree")
    public List<String> getThree(HttpServletRequest request) {
        String[] arrayParam = request.getParameterValues("tel[]");
        System.out.println("너의 타입은" + arrayParam.getClass().getName());
        var c = tourService.foodList(arrayParam);

        return c;
    }

    @PostMapping("/tourDetail1")
    public FoodRow foodDetail(@RequestParam("food_id") Long foodId) {
        FoodRow row = foodService.selectById(foodId);
        System.out.println("row" + row);
        return row;
    }

    @PostMapping("/tourDetail2")
    public String foodReview(@RequestParam("food_id") Long foodId) {
        String review = reviewService.showCategory(foodId);
        System.out.println("review" + review);
        return review;
    }

    @GetMapping("/tourDetail3")
    public Long foodID(@RequestParam("store_tel") String storeTel) {
        System.out.println("투어api컨트롤러 foodID storeTel = " + storeTel);
        Long foodid = tourService.findByFoodId(storeTel);
        System.out.println("rr" + foodid);
        return foodid;
    }

    @PostMapping("/tourDetail4")
    public Double foodReviewstar(@RequestParam("food_id") Long foodId) {
        return reviewService.getScore(foodId);
    }

}