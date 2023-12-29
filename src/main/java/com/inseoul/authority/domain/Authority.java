package com.inseoul.authority.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority {
    private Long id; // Pk
    private String name; // 권한 이름 "ROLE_MEMBER" 등
}
