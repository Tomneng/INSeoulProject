package com.inseoul.tour.repository;

import com.inseoul.tour.domain.Item;

public interface TourApiRepository {
    int save(Item item);
}
