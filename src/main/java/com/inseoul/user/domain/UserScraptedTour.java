package com.inseoul.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserScraptedTour {
    private Long userId;
    private Long tourId;
    private Long tourScraptedId;
}
