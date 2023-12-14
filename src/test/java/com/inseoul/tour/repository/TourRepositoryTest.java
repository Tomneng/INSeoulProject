package com.inseoul.tour.repository;

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

//        System.out.println(tourRepository.selectFromRow("강남", ""));
    }
}
