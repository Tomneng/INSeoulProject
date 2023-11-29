package com.inseoul.real_estate.service;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.real_estate.repository.HouseRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl implements HouseService {

    private HouseRepository houseRepository;

    public HouseServiceImpl(SqlSession sqlSession){
        houseRepository = sqlSession.getMapper(HouseRepository.class);
        System.out.println("Service 생성 완료");
    }
    @Override
    public int save(Housedata housedata) {
        return houseRepository.write(housedata);
    }
}
