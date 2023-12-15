package com.inseoul.user.repository;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.user.domain.UserScraptedHouse;

import java.util.List;


public interface UserScraptedRepository {

    int addHouseScrapt(UserScraptedHouse userScraptedHouse);

    List<Long> getids(Long userId);

    Row selectScrapted(Long id);

    int scrapCheck(Long userId, Long houseId);

    int deleteScrap(Long userId, Long houseId);

}