package com.inseoul.real_estate.repository;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.domain.TbLnOpendataRentV;

public interface HouseRepository {
    int write(Row row);

    int compare(String string);

    Row selectById(Long id);

    int updateScore(Row row);

}
