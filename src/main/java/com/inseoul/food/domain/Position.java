package com.inseoul.food.domain;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
public class Position {

    private double latitude;
    private double longitude;

    //좌표 가져오기
    public Position(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
