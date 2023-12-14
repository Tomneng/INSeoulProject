package com.inseoul.board.service;

import com.inseoul.board.domain.user.User;

public interface UserService {
    // username(회원 아이디) 의 User 정보 읽어오기
    User findByEmail(String email);
    User findByNickname(String nickname);

    // 특정 username(회원 아이디) 의 회원이 존재하는지 확인
    boolean isEmailExist(String email);
    boolean isNicknameExist(String nickname);

    // 신규 회원 등록
    int register(User user);

//    // 특정 사용자(id)의 authority(들)
//    List<Authority> selectAuthoritiesById(Long id);
}