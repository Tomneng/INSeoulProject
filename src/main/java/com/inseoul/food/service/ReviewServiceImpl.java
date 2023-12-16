package com.inseoul.food.service;

import com.inseoul.food.domain.Review;
import com.inseoul.food.repository.ReviewRepository;
import com.inseoul.food.util.U;
import com.inseoul.user.domain.User;
import jakarta.servlet.http.HttpSession;
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
    //review1 객체 생성
    @Override
    public int write(Long foodId, User user, Double reviewStar, String reviewCategory, String reviewContent) {
//        HttpSession session = U.getSession();
        Review review1 = new Review();
        review1.setFoodId(foodId);
        review1.setUser(user);
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
    //평균 업데이트
    @Override
    public double updateRating(Long foodId) {
        return reviewRepository.updateRating(foodId);
    }
}
