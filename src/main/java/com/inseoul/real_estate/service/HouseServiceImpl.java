package com.inseoul.real_estate.service;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.domain.TbLnOpendataRentV;
import com.inseoul.real_estate.repository.HouseRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    private HouseRepository houseRepository;

    public HouseServiceImpl(SqlSession sqlSession){
        houseRepository = sqlSession.getMapper(HouseRepository.class);
        System.out.println("Service 생성 완료");
    }


    @Override
    public int save(Row row) {
        return houseRepository.write(row);
    }

    @Override
    public int isDuplicated(String string) {
        return houseRepository.compare(string);
    }

    @Override
    public Row findById(Long id) {
        return houseRepository.selectById(id);
    }

    @Override
    public int putScore(Row row) {
        return houseRepository.updateScore(row);
    }


}
