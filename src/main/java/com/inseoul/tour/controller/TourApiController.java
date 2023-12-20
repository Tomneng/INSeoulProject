package com.inseoul.tour.controller;

import com.inseoul.tour.domain.Item;
import com.inseoul.tour.service.TourApiService;
import com.inseoul.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apisave")
public class TourApiController {

    @Autowired
    public TourApiService tourApiService;
    @GetMapping("/apisave")
    @ResponseBody
    public void getTourApi(Item item2) {

        tourApiService.getTourApi(item2);

//        // Spring API로부터 데이터 호출
//        List<Item> apiData = yourApiService.getDataFromApi();
//
//        // 데이터베이스에 저장된 데이터 호출
//        List<YourData> dbData = yourDataService.getAllData();
//
//        // 데이터 개수 비교
//        if (apiData.size() != dbData.size()) {
//            // 데이터가 다를 경우 새로운 데이터로 업데이트
//            yourDataService.saveAll(apiData);
//            return "데이터가 갱신되었습니다.";
//        } else {
//            return "데이터가 이미 최신입니다.";
//        }
    }
}
