package com.inseoul.mail.controller;

import com.inseoul.mail.domain.EmailAuthRequestDto;
import com.inseoul.mail.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    //아직 손봐야됨 난 다했는데~~~~~~~

    @RequestMapping("/mailConfirm")
    public String mailConfirm(@RequestBody EmailAuthRequestDto emailDto, Model model) throws MessagingException, UnsupportedEncodingException {
        emailDto.setEmail((String) model.getAttribute("email"));
        String authCode = emailService.sendEmail(emailDto.getEmail());
        model.addAttribute("authCode", authCode);
        return "/user/codeConfirm";
    }

}