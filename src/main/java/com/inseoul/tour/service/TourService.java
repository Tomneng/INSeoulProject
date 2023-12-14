package com.inseoul.tour.service;


import com.inseoul.tour.domain.Item;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

public interface TourService {
    int save(Item item);

    int isDuplicated(String string);

    Item findById(Long tourId);

    int search(Item item);

    void getTourApi(Item item, Model model, Integer page);

    List<Item> list(Item item, Model model, Integer page);

}
