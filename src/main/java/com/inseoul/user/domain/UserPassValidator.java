package com.inseoul.user.domain;

import com.inseoul.real_estate.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserPassValidator implements Validator {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports(" + clazz.getName() + ")");
        boolean result = User.class.isAssignableFrom(clazz);
        System.out.println(result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("validate() 호출");
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "이메일은 필수입니다.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "이름은 필수입니다.");


    }
}
