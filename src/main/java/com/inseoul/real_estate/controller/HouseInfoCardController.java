package com.inseoul.real_estate.controller;


import com.inseoul.real_estate.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/house")
public class HouseInfoCardController {

    @Autowired
    public HouseService houseService;

    @GetMapping("/apisaving")
    public void save() {
    }


}
