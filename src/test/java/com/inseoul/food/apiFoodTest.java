package com.inseoul.food;

import com.inseoul.food.domain.Review;
import com.inseoul.food.repository.FoodRepository;
import com.inseoul.food.repository.ReviewRepository;
import com.inseoul.food.service.FoodService;
import com.inseoul.food.service.ReviewService;
import com.inseoul.user.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class apiFoodTest {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private FoodService foodService;
    @Autowired
    private ReviewService reviewService;

    @Test
    void test1(){
        FoodRepository foodRepository = sqlSession.getMapper(FoodRepository.class);
        System.out.println(foodRepository.findAll());

    }



}

//