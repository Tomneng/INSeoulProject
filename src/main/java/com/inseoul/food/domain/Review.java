package com.inseoul.food.domain;

import com.inseoul.user.domain.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//리뷰 글
@Data   //getter, setter
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //모든 매개변수 생성자
@Builder
public class Review {
    //리뷰 아이디(리뷰 번호)
    private Long reviewId;
    //회원 아이디(리뷰 작성자)
//    @ToString.Exclude
    private User user;

    private Long foodId;
    //리뷰 카테고리
    private String reviewCategory;
    //리뷰 별점
    private double reviewStar;
    //리뷰 글
    private String reviewContent;
}
