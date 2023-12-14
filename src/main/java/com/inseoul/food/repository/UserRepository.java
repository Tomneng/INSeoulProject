package com.inseoul.food.repository;

import com.inseoul.food.domain.User;

public interface UserRepository {
    //특정 id의 user 리턴
    User findById(Long userId);

}
