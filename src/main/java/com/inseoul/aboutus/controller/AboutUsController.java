package com.inseoul.aboutus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aboutus")
public class AboutUsController {

//    @Autowired
//    private AboutUsService aboutUsService;

    public AboutUsController() {
        System.out.println("AboutUsController() 생성");
    }

    @GetMapping("/aboutus")
    public String aboutUs() {
        return "aboutus/aboutus"; // Thymeleaf 템플릿 파일의 상대 경로 (확장자 제외)
    }
//    @PostMapping("/aboutus")
//    public String contactusOk(
//            @Valid ContactUs contactUs,
//            BindingResult result,
//            Model model,
//            RedirectAttributes redirectAttrs
//    ) {
//
//        if(result.hasErrors()){
//            redirectAttrs.addFlashAttribute("content", contactUs.getContent());
//
//            for(var err : result.getFieldErrors()){
//                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
//            }
//
//            return "redirect:/aboutus/aboutus";
//        }
//
//        model.addAttribute("result", aboutUsService.write(contactUs));
//        return "aboutus/contactUsOk";
//    }
}
