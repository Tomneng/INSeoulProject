package com.inseoul.realEstate;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.repository.HouseRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class apiSavetest {
    @Autowired
    private SqlSession sqlSession;
    @Test
    void test1(){
        HouseRepository houseRepository = sqlSession.getMapper(HouseRepository.class);

        int cnt = houseRepository.compare("202311170용산구12000도원동1대지002300001320231125전세84.69570000삼성래미안2001아파트23.12~25.12갱신");
        System.out.println("compare() 결과 = " + cnt);
    }

    @Test
    void test2(){
        HouseRepository houseRepository = sqlSession.getMapper(HouseRepository.class);
        System.out.println(houseRepository.selectById(1L));
    }

    @Test
    void test3(){
        HouseRepository houseRepository = sqlSession.getMapper(HouseRepository.class);
        Row row = new Row();
        row.setHouseId(1L);
        row.setContractScore(80);
        row.setPlaceScore(90);

        System.out.println(houseRepository.updateScore(row));
    }

    @Test
    void test4(){
        HouseRepository houseRepository = sqlSession.getMapper(HouseRepository.class);
        Row row = new Row();
        row.setAccYear("2023");
        row.setSsgName("관악구");
        row.setDongCode(10200);
        row.setHouseKindName("아파트");

        System.out.println(houseRepository.filteredSearch(row.getAccYear(),row.getSsgName(),row.getDongCode(),row.getHouseKindName(), 1, 10));
    }
    @Test
    void test5(){
        HouseRepository houseRepository = sqlSession.getMapper(HouseRepository.class);
        Row row = new Row();
        row.setAccYear("2023");
        row.setSsgName("관악구");
        row.setDongCode(10200);
        row.setHouseKindName("아파트");
        System.out.println(houseRepository.countAll(row));
    }


}
