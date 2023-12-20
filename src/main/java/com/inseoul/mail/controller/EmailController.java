package com.inseoul.mail.controller;

import com.inseoul.mail.domain.AuthCodeQryResult;
import com.inseoul.mail.service.EmailService;
import com.inseoul.user.domain.ScrapQryResult;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/authConfirm")
public class EmailController {

    private final EmailService emailService;

    @RequestMapping("/authcode")
    public AuthCodeQryResult mailConfirm(
            @RequestParam("email") String email
    ) throws MessagingException, UnsupportedEncodingException {
        AuthCodeQryResult qryResult = emailService.sendEmail(email);
        return qryResult;
    }

    @GetMapping("getPass")
    public AuthCodeQryResult givePassword(
            @RequestParam("email") String email
    ) throws MessagingException, UnsupportedEncodingException{
        AuthCodeQryResult qryResult = emailService.sendPass(email);
        return qryResult;
    }

}