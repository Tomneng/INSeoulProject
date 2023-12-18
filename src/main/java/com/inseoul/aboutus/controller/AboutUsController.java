package com.inseoul.aboutus.controller;

import com.inseoul.aboutus.domain.ContactUs;
import com.inseoul.aboutus.service.AboutUsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/aboutus")
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;

    public AboutUsController() {
        System.out.println("AboutUsController() 생성");
    }

    @GetMapping("/aboutus")
    public void aboutUs() {}


    @PostMapping("/aboutus")
    public String contactusOk(
            @Valid ContactUs contactUs,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttrs
    ) {

        if(result.hasErrors()){
            redirectAttrs.addFlashAttribute("content", contactUs.getContent());

            for(var err : result.getFieldErrors()){
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/aboutus/aboutus";
        }
        model.addAttribute("result", aboutUsService.write(contactUs));
        return "aboutus/contactUsOk";
    }
}
