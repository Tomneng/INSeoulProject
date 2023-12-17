package com.inseoul.real_estate.repository;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.domain.TbLnOpendataRentV;

import java.util.List;

public interface HouseRepository {
    int write(Row row);

    Row selectById(Long id);

    int updateScore(Long houseId, Long userId, int contractScore, int placeScore);

    // from에서 rows만큼 필터링해서 select
    List<Row> filteredSearch(String accYear, String ssgName, int dongCode, String houseKindName, int from, int rows);

    int countAll(Row row);
    // 해당 부동산데이터에 유저가 값을 넣은 적이있는지 확인 (있다면 수정, 없다면 추가)
    int checkScore(Long houseId, Long userId);

    int initScore(Long houseId, Long userId, int contractScore, int placeScore);


}
