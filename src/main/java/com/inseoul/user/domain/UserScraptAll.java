package com.inseoul.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserScraptAll {
    private List<UserScraptedFood> foods;
    private List<UserScraptedHouse> houses;
    private List<UserScraptedTour> tours;
}
