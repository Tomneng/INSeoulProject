package com.inseoul.food.service;

import com.inseoul.food.domain.FoodData;
import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.repository.FoodRepository;
import com.inseoul.food.repository.ReviewRepository;
import com.inseoul.food.util.U;
import com.inseoul.real_estate.domain.Row;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{
    private FoodRepository foodRepository;

    //sqlsession bean 주입
    @Autowired
    public FoodServiceImpl(SqlSession sqlSession){
        foodRepository = sqlSession.getMapper(FoodRepository.class);
        System.out.println("FoodService 생성");
    }

    @Override
    public List<FoodRow> list(FoodRow foodRow, Model model) {
        return foodRepository.findAll();
    }

    // 특정 음식점 정보
    @Override
    public FoodRow selectById(Long foodId) {
        return foodRepository.findById(foodId);
    }

    // 평점 보여주기
    @Override
    public double showRating(Long foodId) {
        return foodRepository.findByRating(foodId);
    }


    @Override
    public void getapi(Model model) {
        String url = String.format("http://openapi.seoul.go.kr:8088/%s/json/TbVwRestaurants/1/1000/", "4b574b46536b6873383346726a7a45");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<FoodData> response = restTemplate.getForEntity(url, FoodData.class);
        FoodRow row = null;

        if(response.getBody().getTbVwRestaurants() == null){
            model.addAttribute("result", 0);
        }

        for(int i = 0; i < response.getBody().getTbVwRestaurants().getRow().size(); i++) {
            row = response.getBody().getTbVwRestaurants().getRow().get(i);
            // 우편번호 제거
            String address = "" + row.getStoreAddress().replaceAll("\\d{1,5}-\\d{1,5}", "").trim();
            row.setStoreAddress(address);

//            System.out.println("write(row)의 row는 " + row);
            // 저장
            foodRepository.write(row);
        }
    }
}