package com.inseoul.real_estate.util;

import com.inseoul.config.PrincipalDetails;
import com.inseoul.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public static void printFileInfo(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();   // 원본 이름

        if(originalFileName == null || originalFileName.length() == 0){
            System.out.println("\t파일이 없습니다");
            return;
        }

        System.out.println("\tOriginal File Name : " + originalFileName);
        System.out.println("\tCleanPath : " + StringUtils.cleanPath(originalFileName));
        System.out.println("\tFile Size : " + file.getSize() + " bytes");  // 용량 (byte)
        System.out.println("\tMIME: " + file.getContentType());  // content type (mime type)

        // 이미지 파일 여부
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(file.getInputStream());

            if(bufferedImage != null){
                System.out.printf("\t이미지 파일입니다: %d x %d\n", bufferedImage.getWidth(), bufferedImage.getHeight());
            } else {
                System.out.println("\t이미지 파일이 아닙니다");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
