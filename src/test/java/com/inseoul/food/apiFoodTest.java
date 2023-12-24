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


    @Test
    void test09(){
        ReviewRepository reviewRepository = sqlSession.getMapper(ReviewRepository.class);
        Review review = Review.builder()
                .foodId(24L)
                .userId(1L)
                .reviewCategory("음식이 맛있어요")
                .reviewStar(4.5)
                .reviewContent("산골막국수")
                .build();
        System.out.println(reviewRepository.updateRating(review.getFoodId(), review.getUserId(), review.getReviewStar()));
    }


}

//