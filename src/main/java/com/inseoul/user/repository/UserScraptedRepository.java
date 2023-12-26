package com.inseoul.user.repository;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.tour.domain.Item;
import com.inseoul.user.domain.UserScraptedHouse;
import com.inseoul.user.domain.UserScraptedTour;

import java.util.List;


public interface UserScraptedRepository {

    int addHouseScrapt(UserScraptedHouse userScraptedHouse);

    //이 유저저가 스크랩한거 불러오기
    List<Long> getids(Long userId);

    Row selectScrapted(Long id);

    int scrapCheck(Long userId, Long houseId);

    int deleteScrap(Long userId, Long houseId);

    int scrapCheckTour(Long userId, Long tourId);

    int deleteScrapTour(Long userId, Long tourId);

    int addTourScrapt(UserScraptedTour userScraptedTour);

    List<Long> getIdsTour(Long userId);

    Item selectScraptedTour(Long tourId);

//    List<Item> noMbtiTourCard();
}
