package com.inseoul.food.repository;

import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.domain.Review;

import java.util.List;

public interface ReviewRepository {
    // 특정음식점 리뷰의 목록
//    List<Review> findAll();

    //작성한 리뷰 저장
    int reviewSave(Review review);

    //리뷰 조회(음식점아이디로 카테고리를 보여줌)
    List<String> swCategory(Long foodId);

    // 리뷰 존재 유무
    int countReview(Long userId, Long foodId);

    // 평균
    int updateAvg(Long foodId);

    int updateRating(Review review);

    Double getScore(Long foodId);

    // 리뷰 5개 뽑는거 만들어야지

    //평점 평균 구하기
//    double getRatingAvg(Long foodId);
//    double updateRating()

    //평점 업데이트
//    int updateRating(FoodRow foodRow);
//    int updateRating(FoodRow foodRow);
}
