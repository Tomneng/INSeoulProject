package com.inseoul.tour.repository;

import com.inseoul.aboutus.repository.AboutUsRepository;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.repository.HouseRepository;
import com.inseoul.tour.domain.Item;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TourRepositoryTest {

    @Autowired
    private SqlSession sqlSession;

    @Test
    void test1() {
        TourRepository tourRepository = sqlSession.getMapper(TourRepository.class);

//        System.out.println(tourRepository.selectFromRow("광화문", "23"));
    }

    @Test
    void test2() {
        TourRepository tourRepository = sqlSession.getMapper(TourRepository.class);

//        System.out.println(tourRepository.selectFromRow("강남", "", 10, 12));
    }

    @Test
    void test3() {
        TourRepository tourRepository = sqlSession.getMapper(TourRepository.class);

        System.out.println(tourRepository.selectFromRow("23", "광화문", 10, 12));
    }

    @Test
    void test4() {
        AboutUsRepository aboutUsRepository = sqlSession.getMapper(AboutUsRepository.class);

//        System.out.println(aboutUsRepository.save(1, "집갈래..."));
    }

    @Test
    void test5(){
        HouseRepository houseRepository = sqlSession.getMapper(HouseRepository.class);
        houseRepository.putTop1("ESTJ", 119L);
        Row row = houseRepository.selectById(119L);
        System.out.println(row.getMbtiH());
    }

    @Test
    void test6(){
        TourRepository tourRepository = sqlSession.getMapper(TourRepository.class);
        System.out.println(tourRepository.top3mbti(100L));
        System.out.println(tourRepository.top3Prop(100L));
    }

    @Test
    void test7(){
        TourRepository tourRepository = sqlSession.getMapper(TourRepository.class);
        System.out.println(tourRepository.selectFromRow("1", "향기억", 10, 12));
    }
}
