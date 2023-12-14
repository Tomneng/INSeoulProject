package com.inseoul.board.service;

import com.inseoul.board.domain.user.User;
import com.inseoul.board.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(SqlSession sqlSession, PasswordEncoder passwordEncoder){  // SqlSession클래스가 mapper의 UserRepository.xml과 연결을 해주는 역할을 함 → SQL문과 프로그래밍 코드를 분리해서 구현할 수 있도록 해줌
        userRepository = sqlSession.getMapper(UserRepository.class);    //      아래 register user에서 null이었기 때문에  값을 받아오려면
//        authorityRepository = sqlSession.getMapper(AuthorityRepository.class);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = this.findByEmail(username);
        Collection<GrantedAuthority> collect = new ArrayList<>();

    //    List<Authority> authorityList = authorityRepository.selectAuthoritiesById(user.getId());

      //  for(Authority auth :authorityList){
      //      collect.add(new GrantedAuthority() {
      //          @Override
      //          public String getAuthority() {
      //              return auth.getName();
      //          }
      //      });
      //  }

  //      authorityList.forEach(auth -> {
  //              collect.add((GrantedAuthority) auth::getName);
  //      });
//         Authentication  authentication= SecurityContextHolder.getContext().getAuthentication();
//        User logindUser = (User)authentication.getPrincipal();

        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),collect);
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public boolean isEmailExist(String email) {
        User user = findByEmail(email);
        return (user != null) ? true : false;
    }

    @Override
    public boolean isNicknameExist(String nickname) {
        User user = findByNickname(nickname);
        System.out.println(nickname);
        return (user != null) ? true : false;
    }



    @Override
    public int register(User user) {
        System.out.println(user);
        String password = user.getPassword();   //패스원드를 가져오고
        String encodedPassword = passwordEncoder.encode(password);  // 인코딩 시킴(암호화 시킴)
        user.setPassword(encodedPassword);
//        user.setPassword(passwordEncoder.encode(user.getPassword())); 위에랑 같음

//        // 신규회원은 ROLE_MEMBER 권한을 부여하기
//        Authority auth = authorityRepository.findByName("ROLE_MEMBER");

//        Long userId = user.getUserId();
//        Long auth_id = auth.getId();
//        authorityRepository.addAuthority(user_id, auth_id);
        return userRepository.save(user);   // 원래 1이였는데 하드코딩은 안된다고 함
    }


}
