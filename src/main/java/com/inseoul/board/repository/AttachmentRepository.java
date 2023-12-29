package com.inseoul.board.repository;

import com.inseoul.board.domain.post.Attachment;

import java.util.List;
import java.util.Map;

public interface AttachmentRepository {

    /**
     * 특정 글(postId) 에 첨부파일(들) 추가 INSERT
     * 글 insert, update 시 사용됨.
     * @param list :  Map<String, Object> 들의 List
     *                      ↓        ↓
     *                   <"sourcename",원본파일명>
     *                   <"filename", 저장된파일명>
     * @param postId : 첨부될 글
     * @return : DML 수행 결과값
     */
    int insert(List<Map<String, Object>> list, Long postId);

    // 첨부파일 저장
    int save(Attachment file);

    // 특정 글(postId) 의 첨부파일들
    List<Attachment> findByPost(Long postId);

    // 특정 첨부파일(id) 한개 SELECT
    Attachment findById(Long id);

    // 선택된 첨부파일들 DELETE
    // 글 '수정'단계에서 사용
    int deleteByIds(Long [] ids);

    // 선택된 첨부파일들 SELECT
    // 글 '수정'단계에서 사용
    List<Attachment> findByIds(Long [] ids);


    // 특정 첨부파일 (file) 을 DB 에서 삭제
    int delete(Attachment file);

}