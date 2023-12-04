package com.inseoul.real_estate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Row {
    private Long houseId;
    @JsonProperty("ACC_YEAR")
    private String accYear;
    @JsonProperty("SGG_CD")
    private Integer ssgCode;
    @JsonProperty("SGG_NM")
    private String ssgName;
    @JsonProperty("BJDONG_CD")
    private Integer dongCode;
    @JsonProperty("BJDONG_NM")
    private String dongName;
    @JsonProperty("LAND_GBN")
    private Integer landKind;
    @JsonProperty("LAND_GBN_NM")
    private String landKindName;
    @JsonProperty("BOBN")
    private String bobn;
    @JsonProperty("BUBN")
    private String bubn;
    @JsonProperty("FLR_NO")
    private Integer floor;
    @JsonProperty("CNTRCT_DE")
    private String contractDate;
    @JsonProperty("RENT_GBN")
    private String rentKind;
    @JsonProperty("RENT_AREA")
    private Double rentArea;
    @JsonProperty("RENT_GTN")
    private Integer rentDeposit;
    @JsonProperty("RENT_FEE")
    private Integer rentFee;
    @JsonProperty("BLDG_NM")
    private String buildingName;
    @JsonProperty("BUILD_YEAR")
    private Integer buildYear;
    @JsonProperty("HOUSE_GBN_NM")
    private String houseKindName;
    @JsonProperty("CNTRCT_PRD")
    private String contractPeriod;
    @JsonProperty("NEW_RON_SECD")
    private String newRonSecd;
    private String distinguish;
    private String address;
    private Integer contractScore;
    private Integer placeScore;
}

