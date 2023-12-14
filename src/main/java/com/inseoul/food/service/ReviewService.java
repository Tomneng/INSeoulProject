package com.inseoul.food.service;

import com.inseoul.food.domain.Review;
import org.springframework.ui.Model;

import java.util.List;

public interface ReviewService {
    //리뷰 조회
    List<Review> list(Model model);

    //리뷰 작성
    int write(Review review);
    //리뷰 카테고리
    String selectById(Long reviewId);
//    평점 계산
    double getRatingAverage(Long foodId);

    // 평점 반영
    double updateRating(Long foodId);


}
