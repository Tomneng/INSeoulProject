package com.inseoul.food.repository;

import com.inseoul.food.domain.FoodData;
import com.inseoul.food.domain.Review;

import java.util.List;

public interface FoodRepository {
    //
    List<FoodData> findAll();

    int save(FoodData foodData);
}
