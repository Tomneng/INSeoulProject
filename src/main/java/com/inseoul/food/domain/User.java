package com.inseoul.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long userId;
    private String nickName;
    private String password;
    private String email;
    private String mbti;
    private LocalDateTime regDate;
}
