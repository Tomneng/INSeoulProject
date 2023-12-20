package com.inseoul.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    private Long userId;
    private String username; // 이게 입력 받는 아이디, 이 프로젝트에서는 이메일정보가 담겨있음.

    @JsonIgnore
    private String password;

    @ToString.Exclude // toString() 결과에서 제외
    @JsonIgnore // json욿 response할 때 제외
    private String re_password; // 비밀번호 확인 입력용 DB에 저장 x

    private String nickname;

    @JsonIgnore
    private LocalDateTime regDate;

    // OAuth2
    private String provider; //어떤 OAuth2 제공자? Kakao, Naver, Google...
    private String providerId; // provider 내에서의 고유 id 값
}
