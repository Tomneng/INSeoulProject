package com.inseoul.user.service;

import com.inseoul.authority.domain.Authority;
import com.inseoul.user.domain.User;

import java.util.List;

public interface UserService {
    // username(회원 아이디) 의 User 정보 읽어오기
    User findByUsername(String username);


    // 특정 username(회원 아이디) 의 회원이 존재하는지 확인
    boolean isExist(String username);


    // 신규 회원 등록
    int register(User user);

    int chPassword(User user);


    // 특정 사용자(id)의 authority(들)
    List<Authority> selectAuthoritiesById(Long id);

}
