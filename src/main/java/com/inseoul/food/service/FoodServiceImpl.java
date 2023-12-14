package com.inseoul.food.service;

import com.inseoul.food.domain.FoodData;
import com.inseoul.food.domain.FoodRow;
import com.inseoul.food.repository.FoodRepository;
import com.inseoul.food.repository.ReviewRepository;
import com.inseoul.real_estate.domain.Row;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{

    private FoodRepository foodRepository;

    private ReviewRepository reviewRepository;

    //sqlsession bean 주입
    @Autowired
    public FoodServiceImpl(SqlSession sqlSession){
        foodRepository = sqlSession.getMapper(FoodRepository.class);
        reviewRepository = sqlSession.getMapper(ReviewRepository.class);
        System.out.println("FoodService 생성");
    }
    //카드
    @Override
    public List<FoodRow> list(Model model) {
        return foodRepository.findAll();
    }

    //특정 음식점 정보
    @Override
    public FoodRow selectById(Long foodId) {
        return foodRepository.findById(foodId);
    }

    //중복값 비교
//    @Override
//    public int isDuplicated(String string) {
//        return foodRepository.compare(string);
//    }

    //
//    @Override
//    public int save(FoodRow row) {
//        return foodRepository.write(row);
//    }

    @Override
    public void getapi(Model model) {
        String url = String.format("http://openapi.seoul.go.kr:8088/%s/json/TbVwRestaurants/1/1000/", "4b574b46536b6873383346726a7a45");
        RestTemplate restTemplate = new RestTemplate();
//        System.out.println("getapi함수가 실행되나 봅시다.");

        ResponseEntity<FoodData> response = restTemplate.getForEntity(url, FoodData.class);
        FoodRow row = null;
//      row =response.getBody().getTbVwRestaurants().getRow().get(0);
//      System.out.println(row.getStoreAddr());

        if(response.getBody().getTbVwRestaurants() == null){
            model.addAttribute("result", 0);
        }
        for(int i = 0; i < response.getBody().getTbVwRestaurants().getRow().size(); i++) {
            row = response.getBody().getTbVwRestaurants().getRow().get(i);
            //중복값
//            String distinguish = "" + row.getStoreName() + row.getStoreAddress() + row.getStoreTel();
//            row.setDistinguish(distinguish);
            //DB 데이터에 중복값이 있는 지
//          if(foodService.isDuplicated(row.getDistinguish()) != 0){
//              continue;
//          }
            //우편번호 제거
//            String address = "" + row.getStoreAddress().replaceAll("^[0-9]-[0-9]$", "");
            String address = "" + row.getStoreAddress().replaceAll("\\d{1,5}-\\d{1,5}", "").trim();
            row.setStoreAddress(address);
//            System.out.println(address);

//            System.out.println("write(row)의 row는 " + row);
//            저장
            foodRepository.write(row);
        }
    }


    //api 호출 및 저장
//    @Override
//    public void getapi(Model model) {
//        String url = String.format("http://openapi.seoul.go.kr:8088/%s/json/TbVwRestaurants/1/1000/", "4b574b46536b6873383346726a7a45");
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<FoodData> response = restTemplate.getForEntity(url, FoodData.class);
//        FoodRow row = null;
////      row =response.getBody().getTbVwRestaurants().getRow().get(0);
////      System.out.println(row.getStoreAddr());
//
//        if(response.getBody().getTbVwRestaurants() == null){
//            model.addAttribute("result", 0);
//        }
//        for(int i = 0; i < response.getBody().getTbVwRestaurants().getRow().size(); i++) {
//            row = response.getBody().getTbVwRestaurants().getRow().get(i);
//            //중복값
////            String distinguish = "" + row.getStoreName() + row.getStoreAddress() + row.getStoreTel();
////            row.setDistinguish(distinguish);
//            //DB 데이터에 중복값이 있는 지
////          if(foodService.isDuplicated(row.getDistinguish()) != 0){
////              continue;
////          }
//            //우편번호 제거
////            String address = "" + row.getStoreAddress()
//
//            foodRepository.write(row);
//        }
    }


//}
