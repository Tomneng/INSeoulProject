package com.inseoul.tour.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private Long tourId;
    private String title;
    @JsonProperty("addr1")
    private String address;
    @JsonProperty("firstimage")
    private String image;
}
