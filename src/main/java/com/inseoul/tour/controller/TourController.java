package com.inseoul.tour.controller;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.tour.domain.*;
import com.inseoul.tour.service.TourService;
import com.inseoul.tour.util.U;
import jakarta.mail.Session;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.*;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/tour")
public class TourController {

    @Autowired
    public TourService tourService;

//    @RequestMapping("/tourList")
//    public void list(@RequestParam(value = "tourSigungucode", required = false) String tourSigungucode,
//                       @RequestParam(value = "tourName", required = false) String tourName,
//                       Integer page, Model model) throws IOException {
//
//        // 검색어 및 시군구 코드를 서비스로 전달
//        List<Item> searchResults = tourService.search(tourSigungucode, tourName, page);
//
//        // 모델에 검색 결과 추가
//        model.addAttribute("tourList", searchResults);
//    }
//    public void list(@Valid Item item2, Model model, Integer page) {
//        System.out.println("사용자가 입력한 검색어 조회 = " + tourService.search(item2));
//        if (tourService.search(item2) <= 0) {    // 검색 조건을 사용해서 검색했을 때 같은 조건으로 한번이라도 검색된적이 있는지 확인
//            tourService.getTourApi(item2, model, page);     // 검색된적이 없으면 API 호출 후 DB에 저장
//        }
//        tourService.list(item2, model, page);    // 검색된적이 있으면 getTourApi 하지 않고 바로 list 동작
//    }

    @RequestMapping("/tourList")
    public void list(@Valid Item item, Integer page, Model model) {
        tourService.getOrederedTour();
        if (item.getMbtiT() == null){
            item.setMbtiT("MBTI");
            tourService.listDefault(item, page, model);
        }
        else if (item.getMbtiT().equals("MBTI")){
            tourService.listDefault(item, page, model);

        }
        else {
            tourService.list(item, page, model);
        }
    }

    @GetMapping("/tourDetail/{tourId}")
    public String detail(@PathVariable Long tourId, Model model) {
        model.addAttribute("item", tourService.findById(tourId));
        return "tour/tourDetail";
    }

    // 페이징
    // pageRows 변경시 동작
//    @PostMapping("/pageRows")
//    public String pageRows(Integer page, Integer pageRows) {
//        U.getSession().setAttribute("pageRows", pageRows);
//        return "redirect:/tour/tourList?page=" + page;
//    }

}
