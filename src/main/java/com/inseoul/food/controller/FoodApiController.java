package com.inseoul.food.controller;

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

    // api 불러오기
    @GetMapping("/food")
    public void apiTest(@Valid Model model){
        foodService.getapi(model);
    }
}
