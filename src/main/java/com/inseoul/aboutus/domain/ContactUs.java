package com.inseoul.aboutus.domain;


import com.inseoul.user.domain.User;
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
    private String name;
    private String email;
    private String content;
    private String answer;
    private boolean isAnswered;
}
