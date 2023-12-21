package com.inseoul.admin.service;

import com.inseoul.admin.domain.Complain;
import org.springframework.ui.Model;

import java.util.List;

public interface AdminService {

    List<Complain> list(Integer page, Model model);

    Complain findComById(Long complainId);

    int answered(Complain complain);
}
