package com.inseoul.user.service;

import com.inseoul.food.domain.FoodRow;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.user.domain.ScrapQryResult;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


public interface UserScraptedService {
    List<Row> listById(Model model);

    List<Long> scraptedList(Long id);

    ScrapQryResult scrapted(Long userId, Long houseId);


    List<FoodRow> listByFoodId(Model model);

    List<Long> scraptedFoodList(Long userId);

    ScrapQryResult scraptedFood(Long userId, Long foodId);

}
