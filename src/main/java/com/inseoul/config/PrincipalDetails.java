package com.inseoul.config;

import com.inseoul.authority.domain.Authority;
import com.inseoul.user.domain.User;
import com.inseoul.user.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PrincipalDetails implements UserDetails, OAuth2User {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 로그인한 사용자 정보
    private User user;

    public User getUser() {
        return user;
    }

    // 일반 로그인용 생성자
    public PrincipalDetails(User user){
        System.out.println("UserDetails(user) 생성: " + user);
        this.user = user;
    }

    // OAuth 로그인용 생성자
    public PrincipalDetails(User user, Map<String, Object> attributes){
        this.user = user; // 이때 User의 정보는 인증 직후 provider로부터 받은 attributes를 토대로 생성하게 된다.
        this.attributes = attributes;
    }


    // 해당 User 의 '권한(들)'을 리턴
    // 현재 로그인한 사용자의 권한정보가 필요할때마다 호출된다. 혹은 필요할때마다 직접 호출해 사용할수도 있다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities() 호출");

        Collection<GrantedAuthority> collect = new ArrayList<>();

        // DB 에서 user 의 권한(들) 읽어오기
        List<Authority> list = userService.selectAuthoritiesById(user.getUserId());

        for(Authority auth : list){
            collect.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return auth.getName();
                }

                // thymeleaf 등에서 확인 활용하기 위하 문자열 (학습목적)
                @Override
                public String toString() {
                    return auth.getName();
                }
            });
        }

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public Long getUserId(){
        return user.getUserId();
    }

    // 계정이 만료되지 않았는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠긴건 아닌지?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 credential 이 만료된건 아닌지?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 활성화 되었는지?
    @Override
    public boolean isEnabled() {
        return true;
    }

    private Map<String, Object> attributes; // OAuth2User 의 getAttributes() 값

    @Override
    public String getName(){
        return null; // 사용 안할 예정
    }

    @Override
    public Map<String, Object> getAttributes(){
        return attributes; // 어디서 받아올까? -> 생성자!
    }
}








