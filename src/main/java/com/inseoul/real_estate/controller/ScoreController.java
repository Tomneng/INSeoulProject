package com.inseoul.real_estate.controller;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/avgs")
    public List<Integer> giveAvg(
            @RequestParam("houseId") Long houseId
    ){
        Row row = houseService.findById(houseId);
        List<Integer> list = houseService.getAvgScores(row);
        return list;
    }
}
