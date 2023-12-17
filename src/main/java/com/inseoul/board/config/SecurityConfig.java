package com.inseoul.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }   //패스워드를 암호화 시킬 수 있는 아이

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())     // CSRF 비활성화, Post
                /**********************************************
                 * ① request URL 에 대한 접근 권한 세팅  : authorizeHttpRequests()
                 * .authorizeHttpRequests( AuthorizationManagerRequestMatcherRegistry)
                 **********************************************/
                .authorizeHttpRequests(auth -> auth
                        // URL 과 접근권한 세팅(들)
                        // ↓ /board/detail/** URL로 들어오는 요청은 '인증'만 필요.
//                        .requestMatchers("/board/detail/**").authenticated()
                        // ↓ "/board/write/**", "/board/update/**", "/board/delete/**" URL로 들어오는 요청은 '인증' 뿐 아니라 ROLE_MEMBER 나 ROLE_ADMIN 권한을 갖고 있어야 한다. ('인가')
//                        .requestMatchers("/board/write/**", "/board/update/**", "/board/delete/**").hasAnyRole("MEMBER", "ADMIN")
                        // ↓ 그 밖의 다른 요청은 모두 permit!
                        .anyRequest().permitAll()
                )
                /********************************************
                 * ② 폼 로그인 설정
                 * .formLogin(HttpSecurityFormLoginConfigurer)
                 *  form 기반 인증 페이지 활성화.
                 *  만약 .loginPage(url) 가 세팅되어 있지 않으면 '디폴트 로그인' form 페이지가 활성화 된다
                 ********************************************/
                .formLogin(form -> form
                        .loginPage("/user/login")   // 로그인 필요한 상황(인증 필요상황) 발생시, 매개변수 url (로그인 폼) 으로 request 발생
                        .loginProcessingUrl("/user/login")  // "/user/login" url 로 POST request 가 들어오면 시큐리티가 낚아채서 처리, 대신 로그인을 진행해준다(인증).
                        // 이와 같이 하면 Controller 에서 /user/login (POST) 를 굳이 만들지 않아도 된다!
                        // 위 요청이 오면 자동으로 UserDetailsService 타입 빈객체의 loadUserByUsername() 가 실행되어 인증여부 확인진행 <- 이를 제공해주어야 한다.
                        .defaultSuccessUrl("/home/main") // '직접 /login' → /login(post) 에서 성공하면 "/" 로 이동시키기
                        // 만약 다른 특정페이지에 진입하려다 로그인 하여 성공하면 해당 페이지로 이동 (너무 편리!)

                        // 로그인 성공직후 수행할코드
                        //.successHandler(AuthenticationSuccessHandler)  // 로그인 성공후 수행할 코드.
                        .successHandler((request, response, authentication) ->
                                response.sendRedirect("/home/main"))

                        // 로그인 실패하면 수행할 코드
                        // .failureHandler(AuthenticationFailureHandler)
                        .failureHandler((request, response, exception) ->
                                response.sendRedirect("/user/login?fail"))
                )
                /********************************************
                 * ③ 로그아웃 설정
                 * .logout(LogoutConfigurer)
                 ********************************************/
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/user/logout")   // 로그아웃 수행 url
                        //.logoutSuccessUrl("/user/login?logout")   // 로그아웃 성공후 redirect url
                        .invalidateHttpSession(false)   // session invalidate (디폴트 true)
                        // 이따가 CustomLogoutSuccessHandler 에서 꺼낼 정보가 있기 때문에
                        // false 로 세팅한다

                        // 로그아웃 성공후 수행할 코드
                        // .logoutSuccessHandler(LogoutSuccessHandler)
//                        .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                )

                /********************************************
                 * ④ 예외처리 설정
                 * .exceptionHandling(ExceptionHandlingConfigure)
                 ********************************************/
//                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
//                        // 권한(Authorization) 오류 발생시 수행할 코드
//                        // .accessDeniedHandler(AccessDeniedHandler)
//                        .accessDeniedHandler(new CustomAccessDeniedHandler())
//                )


                .build();
    }

}
