package com.inseoul.food.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodRow {

    private Long foodId;    //PK
    @JsonProperty("POST_SJ")
    public String storeName;
    @JsonProperty("ADDRESS")
    public String storeAddress;
    @JsonProperty("CMMN_TELNO")
    public String storeTel;
    @JsonProperty("LANG_CODE_ID")
    public String langcodeId;

    //리뷰 별점 평균
    private double reviewAvg;


    //첨부 파일(이미지)
    @ToString.Exclude
    @Builder.Default
    private List<Review> fileList = new ArrayList<>();

}
