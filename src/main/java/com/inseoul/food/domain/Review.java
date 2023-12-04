package com.inseoul.food.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    //리뷰 아이디
    private Long rvId;
    //리뷰 카테고리
    private String rvCategory;
    //리뷰 별점
    private double rvStar;
    //리뷰 코멘트
    private String rvComment;
    //첨부 이미지
    private String rvImage;
}
