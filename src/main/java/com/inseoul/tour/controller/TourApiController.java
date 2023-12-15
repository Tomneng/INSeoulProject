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
    }
}
