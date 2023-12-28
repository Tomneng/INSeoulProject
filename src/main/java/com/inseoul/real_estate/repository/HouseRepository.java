package com.inseoul.real_estate.repository;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.domain.TbLnOpendataRentV;

import java.util.List;

public interface HouseRepository {
    int write(Row row);

    Row selectById(Long id);


    // from에서 rows만큼 필터링해서 select
    List<Row> filteredSearch(Row row, int from, int rows);

    List<Row> defaultSearch(Row row, int from, int rows);

    int countAllwithMbti(Row row);

    int countAll(Row row);

    // 해당 부동산데이터에 유저가 값을 넣은 적이있는지 확인 (있다면 수정, 없다면 추가)
    int checkScore(Long houseId, Long userId);

    // 부동산점수 테이블에 점수 추가 - 이때도 updateRealScore 동작 시켜줘야 됨
    int initScore(Long houseId, Long userId, int contractScore, int placeScore);

    // 부동산점수테이블에 바뀐 점수 반영
    int updateScore(Long houseId, Long userId, int contractScore, int placeScore);

    // 실제 부동산데이터 테이블 점수 업데이트
    int updateRealScore(Long houseId);
    // 삭제는 굳이 만들필요가 없는거 같은게 어차피 0넣어도 상관없고 굳이 점수를 수정할 일도 별로 없을듯

    Integer avgCScore(Row row);
    Integer avgPScore(Row row);

    List<String> top3mbti(Long houseId);

    int putTop1(String mbtiH, Long houseId);

    List<Integer> top3Prop(Long houseId);

    List<Row> houseOnmain();

}
