package com.inseoul.food.service;

import com.inseoul.food.domain.Attachment;
import com.inseoul.food.repository.AttachmentRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(SqlSession sqlSession){
        attachmentRepository = sqlSession.getMapper(AttachmentRepository.class);
    }

    @Override
    public Attachment findById(Long reviewImgId) {
        return null;
    }
}
