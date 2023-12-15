package com.inseoul.food.service;

import com.inseoul.food.domain.Review;
import com.inseoul.food.repository.ReviewRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewServiceImpl(SqlSession sqlSession){
        reviewRepository = sqlSession.getMapper(ReviewRepository.class);
        System.out.println("ReviewService() 생성");
    }

    @Override
    public List<Review> list(Model model) {
        return reviewRepository.findAll();
    }
    //리뷰 작성
    @Override
    public int write(Long foodId, Double reviewStar, String reviewCategory, String reviewContent) {
        Review review1 = new Review();
        review1.setFoodId(foodId);
        review1.setReviewStar(reviewStar);
        review1.setReviewCategory(reviewCategory);
        review1.setReviewContent(reviewContent);

        return reviewRepository.reviewSave(review1);
    }
    //리뷰 카테고리 보여주기
    @Override
    public String selectById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }
    //평점 평균
    @Override
    public double getRatingAverage(Long foodId) {
        return reviewRepository.getRatingAvg(foodId);
    }

    @Override
    public double updateRating(Long foodId) {
        return reviewRepository.updateRating(foodId);
    }
}
