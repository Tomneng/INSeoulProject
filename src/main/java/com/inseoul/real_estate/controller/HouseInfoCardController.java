package com.inseoul.real_estate.controller;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.service.HouseService;
import com.inseoul.real_estate.util.U;
import com.inseoul.user.domain.ScrapQryResult;
import com.inseoul.user.service.UserScraptedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/realEstate")
public class HouseInfoCardController {

    @Autowired
    public HouseService houseService;

    @Autowired
    private UserScraptedService userScraptedService;

    /**
     * 실제로 db에 없는 값들은 그때그때마다 getapi를 써서 api 호출 및 DB에 저장
     * moreThanOnce()메소드로 일단 이 검색조건으로 검색됬는지를 확인하기 위해 DB를 조회함
     * 그 후, 검색여부에 따라서 .getapi를 호출함(이 과정이 없으면 페이지 넘길때마다 무조건적으로 api호출을 하게됨)
     * @param row2 : 넘겨줄 검색값
     * @param model : 여러 attributes값 담을 model
     * @param page : 페이지값 넘겨주기
     */
    @RequestMapping("/infoList")
    public void getApi(@Valid Row row2, Model model, Integer page) {
        if (houseService.moreThanOnce(row2) <= 0){
            houseService.getapi(row2, model, page);
        }
        houseService.list(row2, page, model);
    }
    @GetMapping("/redetail/{houseId}")
    public String detail(@PathVariable Long houseId, Model model){
        model.addAttribute("row", houseService.findById(houseId));
        return "realEstate/redetail";
    }

    @PostMapping("/putScore")
    public String setScore(@Valid Row row, Model model){
        model.addAttribute("result", houseService.putScore(row));
        return "redirect:/realEstate/redetail/" + row.getHouseId();
    }
}