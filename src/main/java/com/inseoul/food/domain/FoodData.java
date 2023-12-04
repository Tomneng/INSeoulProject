package com.inseoul.food.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //getter, setter
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //매개변수 생성자
@Builder
public class FoodData {
    //음식점 아이디
    private Long foodId;
    //음식점 명
    private String storeName;
    //음식점 주소
    private String storeAddr;
    //음식점 전화번호
    private String storeTel;
    //음식점 대표이미지(url)
    private String foodImage;

}
