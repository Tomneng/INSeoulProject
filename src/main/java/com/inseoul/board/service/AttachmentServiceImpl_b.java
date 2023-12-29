package com.inseoul.board.service;

import com.inseoul.board.domain.post.Attachment;
import com.inseoul.board.repository.AttachmentRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl_b implements AttachmentService_b {

    private AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl_b(SqlSession sqlSession){
        attachmentRepository = sqlSession.getMapper(AttachmentRepository.class);
    }

    @Override
    public Attachment findById(Long id) {
        return attachmentRepository.findById(id);
    }
}
