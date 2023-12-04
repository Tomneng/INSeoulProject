package com.inseoul.food.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inseoul.food.domain.FoodData;
import com.inseoul.food.repository.FoodRepository;
import lombok.Value;
import org.apache.ibatis.session.SqlSession;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodServiceImpl implements FoodService{

    private FoodRepository foodRepository;

    //sqlsession bean 주입
    @Autowired
    public FoodServiceImpl(SqlSession sqlSession){
        foodRepository = sqlSession.getMapper(FoodRepository.class);
    }

    //이미지 파일 여부
    private void setImage(){}


    @Override
    public List<FoodData> list() {
        return foodRepository.findAll();
    }

    @Override
    public int write() {
        return 0;
    }

    @Override
    public int save(FoodData foodData) {
        return 0;
    }
}
