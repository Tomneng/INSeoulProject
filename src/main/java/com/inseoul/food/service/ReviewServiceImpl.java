package com.inseoul.food.service;

import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.domain.Review;
import com.inseoul.food.repository.ReviewRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewServiceImpl(SqlSession sqlSession){
        reviewRepository = sqlSession.getMapper(ReviewRepository.class);
        System.out.println("ReviewService 생성");
    }

//    @Override
//    public List<Review> list(Model model) {
//        return reviewRepository.findAll();
//    }
    //리뷰 작성
    //review1 객체 생성
//    @Override
//    public int write(Review review) {
//        int result = reviewRepository.reviewSave(review);
////        setRating(review.getFoodId());
//        return result;
//    }

    //리뷰 카테고리 보여주기
    @Override
    public String showCategory(Long foodId) {
        return reviewRepository.swCategory(foodId);
    }


    // 리뷰 존재 유무에 따라 평점수정, 추가
    @Override
    public int countRw(Review review) {
        // 1 또는 0
        // 리뷰가 없다면 리뷰를 insert해주고, 리뷰가 있다면 update
        if (reviewRepository.countReview(review.getUserId(), review.getFoodId()) == 0){
            reviewRepository.reviewSave(review); // 여기서 추가해야할 userid가 null이라서 바티스가 꺼지라고 하는거임
            reviewRepository.updateAvg(review.getFoodId());
        } else {
            reviewRepository.updateRating(review.getFoodId(), review.getUserId(), review.getReviewStar());
            reviewRepository.updateAvg(review.getFoodId());
        }
        return 0;
    }

    @Override
    public int reviewdb(Long userId, Long foodId) {
        return reviewRepository.countReview(userId, foodId);
    }



    // 평점 보여주기
//    @Override
//    public int setRating(Long foodId) {
//        return 0;
//    }
//    @Override
//    public double getRatingAverage(Long foodId) {
//        return reviewRepository.getRatingAvg(foodId);
//    }

    //평점 업데이트
//    @Override
//    public double updateRating(Long foodId) {
//        return reviewRepository.updateRating(foodId);
//    }

    //평균 업데이트
//    public int setRating(Long foodId){
////        계산된 평점
//        double reviewAvg = reviewRepository.getRatingAvg(foodId);
////        평점이 없다면
//
//        //첫째 자리
//        reviewAvg = (double) (Math.round(reviewAvg * 10));
//        reviewAvg = reviewAvg / 10;
//
//        FoodRow foodRow = new FoodRow();
//        foodRow.setFoodId(foodId);
//        foodRow.setReviewAvg(reviewAvg);
//
//        return reviewRepository.updateRating(foodRow);
////        return foodRow;
////        System.out.println("??" + foodRow.setReviewAvg(foodRow.getReviewAvg()));
////        return foodRow;
//    }



}
