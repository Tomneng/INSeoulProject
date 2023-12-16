package com.inseoul.food.repository;

import com.inseoul.food.domain.Review;

import java.util.List;

public interface ReviewRepository {
    // 특정음식점 리뷰의 목록
    List<Review> findAll();

    //작성한 리뷰 저장
    int reviewSave(Review review);
    //리뷰 조회(리뷰아이디로 카테고리를 보여줌)
    String findById(Long reviewId);
    //평점 평균 구하기
    double getRatingAvg(Long foodId);
    //평점 평균 반영하기
    double updateRating(Long foodId);
}