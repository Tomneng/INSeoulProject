package com.inseoul.admin.domain;

import com.inseoul.user.domain.User;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complain {
    private Long complainId; // 글 id (PK)
    private String nickname;
    private String username; // email
    private String content;
    private String answer; // 추후에 답변도 이메일처리 해줘야함
    private boolean isAnswered;
}
