package com.inseoul.food;

import com.inseoul.food.domain.Review;
import com.inseoul.food.repository.FoodRepository;
import com.inseoul.food.repository.ReviewRepository;
import com.inseoul.food.service.FoodService;
import com.inseoul.food.service.ReviewService;
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
    void Test2(){
        ReviewRepository reviewRepository = sqlSession.getMapper(ReviewRepository.class);
//        User user = User.builder()
//                .userId(15L)
//                .nickName("sso")
//                .password("1234")
//                .email("sso@gmail.com")
//                .regDate(LocalDateTime.now())
//                .mbti("istj")
//                .build();
//
//        Review review = Review.builder()
//                .reviewId(3L)
//                .foodId(22L)
//                .user(user)
//                .reviewCategory("음식이 맛있어요")
//                .reviewStar(4.5)
//                .reviewContent("산골막국수")
//                .build();
//
//        System.out.println("사용자 : " + user.getUserId());
//        System.out.println("저장 : " +reviewRepository.reviewSave(review));
//        System.out.println("리뷰:" + reviewRepository.findById(review.getReviewId()));
////        System.out.println("리뷰 카테고리 : " + review.getReviewCategory());
//        System.out.println("리뷰 평점 : "  + reviewRepository.getRatingAvg(review.getFoodId()));
//        System.out.println("평점 업데이트 : " + reviewRepository.updateRating(review.getFoodId()));
    }
}

//