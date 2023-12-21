package com.inseoul.board.domain.post;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PostValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports(" + (clazz.getName()) + ")");
        boolean result = Post.class.isAssignableFrom(clazz);
        System.out.println(result);
        return result;
    }
//    Class<?> clazz 클래스의 리터럴 값 클래스를 받아온다

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("타겟 에러" + target + "그냥 에러" + errors);
        System.out.println("PostValidator의 validate() 호출");
        Post post = (Post) target;
        System.out.println(post);

    }

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "글 제목은 필수입니다.");
}
