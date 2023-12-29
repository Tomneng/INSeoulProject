package com.inseoul.food.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //getter, setter
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //매개변수 생성자
@Builder
public class FoodData {
    @JsonProperty("TbVwRestaurants")
    private TbVwRestaurants tbVwRestaurants;
}
