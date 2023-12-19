package com.inseoul.aboutus.service;

import com.inseoul.aboutus.domain.ContactUs;
import com.inseoul.aboutus.repository.AboutUsRepository;
import com.inseoul.real_estate.util.U;
import com.inseoul.user.domain.User;
import com.inseoul.user.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutUsServiceImpl implements AboutUsService {

    private AboutUsRepository aboutUsRepository;


    @Autowired
    public AboutUsServiceImpl(SqlSession sqlSession){
        aboutUsRepository = sqlSession.getMapper(AboutUsRepository.class);
        System.out.println("AboutUsService() 생성");
    }
    @Override
    public int write(ContactUs contactUs) {
        return aboutUsRepository.save(contactUs);
    }
}
