package com.inseoul.user.domain;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserScraptedFood {
    @ToString.Exclude
    private Long userId;    //스크랩한 회원 아이디
    @JsonIgnore
    private Long foodId;    //어떤 음식점

    private Long foodScraptedId;
}
