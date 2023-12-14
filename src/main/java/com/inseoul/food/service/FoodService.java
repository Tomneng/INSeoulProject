package com.inseoul.food.service;

import com.inseoul.food.domain.FoodData;
import com.inseoul.food.domain.FoodRow;
import com.inseoul.real_estate.domain.Row;
import org.springframework.ui.Model;

import java.util.List;

public interface FoodService {

    //음식점 조회
    List<FoodRow> list(Model model);
    //특정아이디 글(select)
    FoodRow selectById(Long foodId);

    //중복값
//    int isDuplicated(String string);

//    int save(FoodRow row);

    //api 저장
    void getapi(Model model);
}
