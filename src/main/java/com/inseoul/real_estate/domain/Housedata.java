package com.inseoul.real_estate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Housedata {
    private Integer houseId;
    private String accYear;
    private Integer ssgCode;
    private String ssgName;
    private Integer dongCode;
    private String dongName;
    private Integer landKind;
    private String landKindName;
    private String bobn;
    private String bubn;
    private Integer floor;
    private String contractDate;
    private String rentKind;
    private Double rentArea;
    private Integer rentDeposit;
    private Integer rentFee;
    private String buildingName;
    private Integer buildYear;
    private String houseKindName;
    private String contractPeriod;
    private String newRonSecd;
}
