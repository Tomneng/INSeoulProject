package com.inseoul.tour.repository;

import com.inseoul.food.domain.FoodRow;
import com.inseoul.tour.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TourRepository {

    int save(Item item);

    // 특정 id 글 내용 읽기 (SELECT)
    Item selectById(Long tourId);

//     전체 글의 개수
    int countAll(Item item);
//    int countAll();

    // 페이징
    // from 부터 rows 개 만큼 SELECT
//    List<Item> selectFromRow(String tourName, String tourSigungucode, int fromRow, int pageRows);
    List<Item> selectFromRow(String tourSigungucode, String tourName, int fromRow, int pageRows);


    //음식점
    List<FoodRow> selectByFood();

}

