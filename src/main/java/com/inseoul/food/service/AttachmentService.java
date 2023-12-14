package com.inseoul.food.service;

import com.inseoul.food.domain.Attachment;

public interface AttachmentService {
    Attachment findById(Long reviewImgId);
}
