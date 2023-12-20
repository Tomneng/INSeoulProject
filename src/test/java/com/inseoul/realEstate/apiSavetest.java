package com.inseoul.realEstate;


import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.repository.HouseRepository;
import com.inseoul.user.domain.UserScraptedHouse;
import com.inseoul.user.repository.UserScraptedRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class apiSavetest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void test2(){
        HouseRepository houseRepository = sqlSession.getMapper(HouseRepository.class);
        System.out.println(houseRepository.selectById(1L));
    }

    @Test
    void test3(){
        HouseRepository houseRepository = sqlSession.getMapper(HouseRepository.class);
        Row row = new Row();
        row.setHouseId(107L);
        System.out.println("추가 전" + row.getPlaceScore());
        System.out.println("추가 전" + row.getContractScore());
        if(houseRepository.checkScore(row.getHouseId(), 1L) == 0){
        System.out.println(houseRepository.initScore(row.getHouseId(), 1L, 50, 50));
        System.out.println(houseRepository.initScore(row.getHouseId(), 2L, 40, 40));
            System.out.println("2개추가");
        }else {
        System.out.println(houseRepository.updateScore(row.getHouseId(), 1L, 40, 40));
            System.out.println("업데이트");
        }
        System.out.println("추가 후" + houseRepository.updateRealScore(107L));
        System.out.println("추가 후" + houseRepository.updateRealScore(107L));
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

    @Test
    void test06(){
        UserScraptedRepository userScraptedRepository = sqlSession.getMapper(UserScraptedRepository.class);
        UserScraptedHouse userScraptedHouse = new UserScraptedHouse();
        userScraptedHouse.setUserId(1L);
        userScraptedHouse.setHouseId(3L);

        System.out.println(userScraptedRepository.addHouseScrapt(userScraptedHouse));
    }

    @Test
    void test07(){
        UserScraptedRepository userScraptedRepository = sqlSession.getMapper(UserScraptedRepository.class);
        List<Long> list =  userScraptedRepository.getids(1L);
        System.out.println(list);
    }


    @Test
    void test08(){
        UserScraptedRepository userScraptedRepository = sqlSession.getMapper(UserScraptedRepository.class);
        List<Long> list = userScraptedRepository.getids(1L);
        List<Row> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            list1.add(i,userScraptedRepository.selectScrapted(list.get(i)));
        }
        System.out.println(list1);
    }

}
