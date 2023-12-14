package com.inseoul.tour.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tour {
    @JsonProperty("response")
    private Response response;
}
