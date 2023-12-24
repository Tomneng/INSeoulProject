package com.inseoul.real_estate.util;

import com.inseoul.config.PrincipalDetails;
import com.inseoul.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public class U {
    // 현재 request를 구하려는 메소드

    /**
     * HttpServletReqeust 객체를 직접 얻는 메소드
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        //서블릿 객체들은 RequestContextHoler로부터 현재의 ServletRequestAttributes 객체를 얻은후, 필요한 객체를 얻을 수 있음

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * 현재 session 구하는 메소드. getSession()은 현재 session이 있으면 그걸 return하고,
     * 없다면 새로운 session을 만들어서 리턴.
     *
     * @return
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    public static User getLoggedUser() {
        // 현재 로그인 한 사용자
        PrincipalDetails userDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return user;
    }

}
