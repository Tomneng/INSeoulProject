package com.inseoul.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.tour.domain.Item;
import com.inseoul.tour.domain.Tour;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ScrapQryList extends ScrapQryResult {
    @ToString.Exclude
    @JsonProperty("data") // json변환시 data라는 이름의 property로 변경!
    List<UserScraptedHouse> list;

    @ToString.Exclude
    @JsonProperty("data")
    List<UserScraptedFood> listFood;

//    @ToString.Exclude
//    @JsonProperty("data")
//    List<Item> list;
}
