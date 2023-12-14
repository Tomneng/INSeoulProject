package com.inseoul.admin.domain;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complain {
    private Long complainId; // 글 id (PK)
//    private User user; // 글작성자 (FK) 나중에 필요
    private String content;
    private String answer;
    private boolean isAnswered;
}
