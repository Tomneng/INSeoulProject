package com.inseoul.food.service;

import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.domain.Review;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ReviewService {
    //리뷰 조회
//    List<Review> list(Model model);

    //리뷰 작성
//    int write(Review review);

    //리뷰 카테고리
    String showCategory(Long foodId);

    // 리뷰 존재 유무
//    int countRw(Review review);
    int countRw(Review review);

    //계산된 평점 반영
//    int updateRat(Long foodId);
//
//    // 있다면 insert
//    int insertRat(FoodRow foodRow);

    // 평점 계산
//    double getRatingAverage(Long foodId);
//
//    // 평점 반영
//    void setRating(Long foodId);
//    int showRating(Long foodId);

    //
    int reviewdb(Long userId, Long foodId);

    Double getScore(Long foodId);
}
