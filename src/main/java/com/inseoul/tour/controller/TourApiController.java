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

//        // Spring API로부터 데이터 호출
//        List<Item> apiData = yourApiService.getDataFromApi();
//
//        // 데이터베이스에 저장된 데이터 호출
//        List<YourData> dbData = yourDataService.getAllData();
//
//        // 데이터 개수 비교
//        if (apiData.size() != dbData.size()) {
//            // 데이터가 다를 경우 새로운 데이터로 업데이트
//            yourDataService.saveAll(apiData);
//            return "데이터가 갱신되었습니다.";
//        } else {
//            return "데이터가 이미 최신입니다.";
//        }
    }

    @GetMapping("/mbtiOrder")
    public Object[] giveTop3(
            @RequestParam("tourId") Long tourId
    ) {
        Object[] hmap = tourService.getTop(tourId);
        return hmap;
    }


    //전화번호
    @PostMapping("/getThree")
    public List<String> getThree(HttpServletRequest request) {
        String[] arrayParam = request.getParameterValues("tel[]");
        var c = tourService.foodList(arrayParam);
        System.out.println("매치된 전화번호 : " + c);
        return c;
    }

    // 음식점 정보 보여주기
    @PostMapping("/tourDetail1")
    public FoodRow foodDetail(@RequestParam("food_id") Long foodId) {
        FoodRow row = foodService.selectById(foodId);
        return row;
    }

    //리뷰 카테고리 보여주기
    @PostMapping("/tourDetail2")
    public String foodReview(@RequestParam("food_id") Long foodId) {
        String review = reviewService.showCategory(foodId);
        return review;
    }

    //전화번호로 음식점 아이디 찾기
    @GetMapping("/tourDetail3")
    public Long foodID(@RequestParam("store_tel") String storeTel) {
        Long foodid = tourService.findByFoodId(storeTel);
        return foodid;
    }

    //리뷰 평점 보여주기
    @PostMapping("/tourDetail4")
    public Double foodReviewstar(@RequestParam("food_id") Long foodId) {
        return reviewService.getScore(foodId);
    }
}
