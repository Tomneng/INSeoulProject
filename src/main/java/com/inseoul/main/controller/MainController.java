package com.inseoul.main.controller;

import com.inseoul.real_estate.service.HouseService;
import com.inseoul.tour.domain.Item;
import com.inseoul.tour.service.TourService;
import com.inseoul.user.service.UserScraptedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private TourService tourService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserScraptedService userScraptedService;

    @RequestMapping("/inseoul")
    public String home() {
        return "redirect:/home/main";
    }

    @RequestMapping("/home/main")
    public String common(Model model) {
        model.addAttribute("randomTourCard", tourService.getRandomTourCard());
        model.addAttribute("houseCard", houseService.houseOnmain());

//        List<Item> noMbtiTourCard = userScraptedService.noMbtiTourCard();
//        model.addAttribute("noMbtiTourCard", noMbtiTourCard);
//        model.addAttribute("noMbtiCard", userScraptedService.noMbtiTourCard());

        return "home/main";
    }

    @RequestMapping("/map")
    public String testScroll() {
        return "redirect:/home/map";
    }

    @RequestMapping("/home/map")
    public void scroll(Model model) {
    }

}
