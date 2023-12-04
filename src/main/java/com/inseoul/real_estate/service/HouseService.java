package com.inseoul.real_estate.service;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.domain.TbLnOpendataRentV;

public interface HouseService {
    int save(Row row);

    int isDuplicated(String string);

    Row findById(Long id);

    int putScore(Row row);
}
