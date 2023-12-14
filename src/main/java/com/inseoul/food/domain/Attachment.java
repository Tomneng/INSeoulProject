package com.inseoul.food.domain;

import lombok.*;


//첨부파일 이미지
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
    private Long reviewImgId;  //PK(첨부파일 아이디)
    private Long reviewId;  //FK(리뷰아이디)
    private String sourceName;  //원본 파일명
    private String fileName;    //저장된 파일명

}
