package com.inseoul.food.repository;

import com.inseoul.food.domain.Attachment;

import java.util.List;
import java.util.Map;

public interface AttachmentRepository {
    int insert (List<Map<String, Object>> list, Long reviewId);

//    첨부파일 저장
    int save(Attachment file);
//    특정음식점의 첨부이미지들
    List<Attachment> findByReview(Long foodId);
//    특정 첨부이미지의 한개 SELECT
    Attachment findById(Long reviewImgId);

    // 선택된 첨부파일들 DELETE
    // 글 '수정'단계에서 사용
    int deleteByIds(Long [] ids);

    // 선택된 첨부파일들 SELECT
    // 글 '수정'단계에서 사용
    List<Attachment> findByIds(Long [] ids);

    // 특정 첨부파일 (file) 을 DB 에서 삭제
    int delete(Attachment file);


}
