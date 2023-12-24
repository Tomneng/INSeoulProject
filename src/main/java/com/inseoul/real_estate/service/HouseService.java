package com.inseoul.real_estate.service;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.domain.TbLnOpendataRentV;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface HouseService {
    int save(Row row);

    int moreThanOnce(Row row);

    Row findById(Long id);

    int putScore(Long houseId, Long userId, int contractScore, int placeScore);

    List<Row> list(Row row, Integer page, Model model);

    void getapi(Row row, Model model, Integer page);

    List<Integer> getAvgScores(Row row);

    Object[] getTop(Long houseId);

    List<Row> houseOnmain();

}
