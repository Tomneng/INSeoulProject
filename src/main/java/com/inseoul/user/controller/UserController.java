package com.inseoul.user.controller;


import com.inseoul.user.domain.User;
import com.inseoul.user.domain.UserValidator;
import com.inseoul.user.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/login2")
    public void login(Model model) {
    }

    // .LoginProcessingUrl("/user/Login") 이 설정된 경우
    // Security가 낚아채 아래 핸들러는 실행되지 않음
    @PostMapping("/login2")
    public void loginProcess() {
        System.out.println("POST: /user/login 요청 발생");
    }

    // onAuthenticationFailure 에서 로그인 실패시 forwarding 용
    // request 에 담겨진 attribute 는 Thymeleaf 에서 그대로 표현 가능.
    @PostMapping("/loginError")
    public String loginError() {
        return "user/login2";
    }

    @RequestMapping("/rejectAuth")
    public String rejectAuth() {
        return "common/rejectAuth";
    }

    @GetMapping("/register")
    public void register() {
    }


    @PostMapping("/register")
    public String registerOk(@Valid User user,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttrs
    ) throws MessagingException, UnsupportedEncodingException {
        // 검증 에러가 있었다면 redirect 한다
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("username", user.getUsername());
            redirectAttrs.addFlashAttribute("name", user.getNickname());

            List<FieldError> errList = result.getFieldErrors();
            for (FieldError err : errList) {
                redirectAttrs.addFlashAttribute("error", err.getCode());  // 가장 처음에 발견된 에러를 담아ㅣ 보낸다
                break;
            }
            return "redirect:/user/register";
        }
        // 에러 없었으면 회원등록 진행
        int cnt = userService.register((User) model.getAttribute("user"));
        model.addAttribute("result", cnt);
        return "user/registerOk";
    }


    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }
} // end Controller
