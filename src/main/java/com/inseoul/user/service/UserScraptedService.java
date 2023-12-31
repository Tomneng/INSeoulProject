package com.inseoul.user.service;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.tour.domain.Item;
import com.inseoul.user.domain.ScrapQryResult;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


public interface UserScraptedService {
    List<Row> listById(Model model);

    List<Long> scraptedList(Long id);

    ScrapQryResult scrapted(Long userId, Long houseId);

    List<Long> scraptedTourList(Long userId);

    ScrapQryResult scraptedTour(Long userId, Long tourId);

    List<Item> listByIdTour(Model model);


//    List<Item> noMbtiTourCard();
}
