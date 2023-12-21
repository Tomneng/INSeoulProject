package com.inseoul.user.repository;

import com.inseoul.food.domain.FoodRow;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.user.domain.UserScraptedFood;
import com.inseoul.user.domain.UserScraptedHouse;

import java.util.List;


public interface UserScraptedRepository {

    int addHouseScrapt(UserScraptedHouse userScraptedHouse);

    //이 유저가 스크랩한거 불러오기
    List<Long> getids(Long userId);

    Row selectScrapted(Long id);

    int scrapCheck(Long userId, Long houseId);

    int deleteScrap(Long userId, Long houseId);

    // 음식점
    int addFoodScrapt(UserScraptedFood userScraptedFood);

    List<Long> getidsFood(Long userId); //유저가 스크랩한 거 불러오기

    FoodRow selectFoodScrapted(Long id);    //스크랩한 정보 보여주기

    int scrapFoodCheck(Long userId, Long foodId);

    int deleteFoodScrap(Long userId, Long foodId);

}
