package com.inseoul.real_estate.repository;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.domain.TbLnOpendataRentV;

import java.util.List;

public interface HouseRepository {
    int write(Row row);

    Row selectById(Long id);

    int updateScore(Row row);

    // from에서 rows만큼 필터링해서 select
    List<Row> filteredSearch(String accYear, String ssgName, int dongCode, String houseKindName, int from, int rows);

    int countAll(Row row);


}
