package com.inseoul.food.repository;

import com.inseoul.food.domain.FoodData;
import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.domain.Review;

import java.util.List;

public interface FoodRepository {
    //조회
    List<FoodRow> findAll();
    //특정 아이디 읽기
    FoodRow findById(Long foodId);
    //insert
    int write(FoodRow row);
}
