package com.inseoul.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserScraptedFood {
    private Long userId;
    private Long houseId;
    private Long foodScraptedId;
}
