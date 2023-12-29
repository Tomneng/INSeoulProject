package com.inseoul.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserScraptedHouse {
    private Long houseScraptedId;

    @ToString.Exclude
    private Long userId; // 스크랩한 유저아이디, 굳이 유저에게 받아올 정보가 없어서 아이디만 있어도 될듯

    @JsonIgnore
    private Long houseId; // 어떤 부동산 데이터인지
}
