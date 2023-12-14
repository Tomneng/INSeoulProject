package com.inseoul.aboutus.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactUs {
    private int complainId;
//    private User userId;
    private String content;
    private String answer;
    private boolean isAnswered;
}
