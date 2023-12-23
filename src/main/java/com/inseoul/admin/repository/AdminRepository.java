package com.inseoul.admin.repository;

import com.inseoul.admin.domain.Complain;

import java.util.List;

public interface AdminRepository {

    int allComplains();

    List<Complain> complains(int from, int rows);

    Complain findByIdCom(Long complainId);

    int resolved(Complain complain);
}
