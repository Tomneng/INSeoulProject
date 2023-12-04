package com.inseoul.food.service;

import com.inseoul.food.domain.FoodData;

import java.util.List;

public interface FoodService {

    //음식점 정보 조회
    List<FoodData> list();
    //리뷰 작성
    //review category, star, comment, 첨부파일
    int write();

    int save(FoodData foodData);

    //특정

}
