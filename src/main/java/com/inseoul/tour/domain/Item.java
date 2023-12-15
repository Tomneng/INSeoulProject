package com.inseoul.tour.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    private Long tourId;
    @JsonProperty("title")
    private String tourName;
    @JsonProperty("addr1")
    private String tourAddr1;
    @JsonProperty("addr2")
    private String tourAddr2;
    @JsonProperty("firstimage")
    private String tourImage1;
    @JsonProperty("firstimage2")
    private String tourImage2;
    @JsonProperty("contentid")
    private int tourContentid;
    @JsonProperty("sigungucode")
    private String tourSigungucode;
    @JsonProperty("mapx")
    private double tourMapx;
    @JsonProperty("mapy")
    private double tourMapy;
}
