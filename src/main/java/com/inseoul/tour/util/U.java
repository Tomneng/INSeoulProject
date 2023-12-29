package com.inseoul.tour.util;

//import com.inseoul.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class U {
    // 현재 request를 구하려는 메소드
    public static HttpServletRequest getRequest() {
        //서블릿 객체들은 RequestContextHoler로부터 현재의 ServletRequestAttributes 객체를 얻은후, 필요한 객체를 얻을 수 있음
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attrs.getRequest();
    }

    public static HttpSession getSession(){
        return getRequest().getSession();
    }

}
