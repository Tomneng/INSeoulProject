package com.inseoul.food.repository;

import com.inseoul.food.domain.FoodData;
import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.domain.Review;

import java.util.List;

public interface FoodRepository {
    //조회
    List<FoodRow> findAll();

//    List<FoodRow> selectFromRow(Long foodId, )
    //특정 아이디 읽기
    FoodRow findById(Long foodId);
    //insert
    int write(FoodRow row);

    //평점 평균 보여주기
    double findByRating(Long foodId);
}
