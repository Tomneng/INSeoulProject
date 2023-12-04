package com.inseoul.real_estate.controller;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.service.HouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/realEstate")
public class HouseInfoCardController {

    @Autowired
    public HouseService houseService;


    @GetMapping("/infoList")
    @ResponseBody
    public String getApi() {
        String url = String.format("http://openapi.seoul.go.kr:8088/%s/json/tbLnOpendataRentV/1/120/2023/11170"
                , "5146444173746f6d32346f53767a56");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Housedata> response = restTemplate.getForEntity(url, Housedata.class);
        Row row = null;
        for (int i = 0; i < 120; i++) {
            row = response.getBody().getTbLnOpendataRentV().getRow().get(i);
            // 중복값 저장을 방지하기 위한 String 추가(모든 칼럼값 합친거)
            String distinguish = "" + row.getAccYear() + row.getSsgCode() + row.getSsgName() + row.getDongCode()
                    + row.getDongName() + row.getLandKind() + row.getLandKindName() + row.getBobn() + row.getBubn()
                    + row.getFloor() + row.getContractDate() + row.getRentKind() + row.getRentArea()
                    + row.getRentDeposit() + row.getRentFee() + row.getBuildingName() + row.getBuildYear()
                    + row.getHouseKindName() + row.getContractPeriod() + row.getNewRonSecd();
            row.setDistinguish(distinguish);
            // 보기 편한 주소값 column
            String address = "" + row.getSsgName() + " " + row.getDongName() + " " + row.getBobn() + "-" + row.getBubn();
            row.setAddress(address);
            // DB 데이터 상에 중복값있는지 확인 후 저장
            if (houseService.isDuplicated(row.getDistinguish()) != 0){
                    continue;
            }
            houseService.save(row);
        }
        return "realEstate/redetail";
    }

    @GetMapping("/redetail/{houseId}")
    public String detail(@PathVariable Long houseId, Model model){
        model.addAttribute("row", houseService.findById(houseId));
        return "realEstate/redetail";
    }

    @PostMapping("/putScore")
    public void setScore(@Valid Row row, Model model){
        model.addAttribute("result", houseService.putScore(row));
    }


}
