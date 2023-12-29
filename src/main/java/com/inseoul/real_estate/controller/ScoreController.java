package com.inseoul.real_estate.controller;

import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.service.HouseService;
import com.inseoul.real_estate.util.U;
import com.inseoul.tour.domain.Item;
import com.inseoul.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/putScore")
    public void setScore(
            @RequestParam("houseId") Long houseId,
            @RequestParam("cScore") int contractScore,
            @RequestParam("pScore") int placeScore,
            @RequestParam("userId") Long userId){

        houseService.putScore(houseId, userId, contractScore, placeScore);
    }

    @GetMapping("/mbtiOrder")
    public Object[] giveTop3(
            @RequestParam("houseId") Long houseId
    ){
        Object[] hmap = houseService.getTop(houseId);
        return hmap;
    }

}
