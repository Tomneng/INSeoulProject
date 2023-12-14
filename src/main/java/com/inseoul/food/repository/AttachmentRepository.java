package com.inseoul.food.repository;

import com.inseoul.food.domain.Attachment;

import java.util.List;
import java.util.Map;

public interface AttachmentRepository {
    int insert (List<Map<String, Object>> list, Long reviewId);

//    첨부파일 저장
    int save(Attachment file);
//    특정 리뷰의 첨부이미지들
    List<Attachment> findByReview(Long reviewId);
//    특정 첨부이미지의 한개 SELECT
    Attachment findById(Long reviewImgId);


}
