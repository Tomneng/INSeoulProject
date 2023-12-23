package com.inseoul.user.controller;

import com.inseoul.user.domain.ScrapQryResult;
import com.inseoul.user.domain.User;
import com.inseoul.real_estate.util.U;
import com.inseoul.user.domain.UserPassValidator;
import com.inseoul.user.domain.UserValidator;
import com.inseoul.user.service.UserScraptedService;
import com.inseoul.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserMypageController {

    @Autowired
    private UserScraptedService userScraptedService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/mypage")
    public void mypage(Model model) {
        User user = U.getLoggedUser();
        userScraptedService.listById(model);
        userScraptedService.listByIdTour(model);
        model.addAttribute("user", user);
    }


    @GetMapping("/userinfo/{userId}")
    public String update(@PathVariable Long userId, Model model) {
//        User user = U.getLoggedUser();

        User user = userService.selectById(userId);
        model.addAttribute("user", user);
        return "user/userinfo";
    }

    @PostMapping("/userinfo")
    public String userinfoOk(
            @Valid User user,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttrs
    ) {
        if (result.hasErrors()) {

            redirectAttrs.addFlashAttribute("username", user.getUsername());
            redirectAttrs.addFlashAttribute("nickname", user.getNickname());

            for (var err : result.getFieldErrors()) {
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/user/userinfo/" + user.getUserId();
        }
        System.out.println(userService.update(user));
        model.addAttribute("result", userService.update(user));
        return "user/userinfoOk";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder){
        System.out.println("initBinder() 호출");
        binder.setValidator(new UserPassValidator());
    }


}
