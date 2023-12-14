package com.inseoul.food.service;

import com.inseoul.food.domain.Attachment;
import com.inseoul.food.repository.AttachmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment findById(Long reviewImgId) {
        return null;
    }
}
