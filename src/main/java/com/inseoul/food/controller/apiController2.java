package com.inseoul.food.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inseoul.food.domain.KakaoCategory;
import com.inseoul.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Controller
public class apiController2 {
    @Value("61751b5d791d1ce89ad7cb69233f3ee0")
    private String apiKey;

    @Value("https://dapi.kakao.com/v2/local/search/category")
    private String kakaoFoodInfoURi;

    @Autowired
    private FoodService foodService;
    @RequestMapping("/food")
//    @ResponseBody
//    public ResponseEntity<String> apiTest(String q){
//        String url = String.format("https://dapi.kakao.com/v2/local/search/category.json?category_group_code=FD6&radius=10000"
//                , "61751b5d791d1ce89ad7cb69233f3ee0", URLEncoder.encode(q));
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(url, String.class);
//
//        return ResponseEntity
//                .ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(result);
//    }



    public String apiTest(@RequestParam String category_group_code){

        RestTemplate rt = new RestTemplate();

        //헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK" + apiKey);
        headers.add("Content-type", "application/json;charset=UTF-8");

        //바디
        HttpEntity<String> kakaoRequest = new HttpEntity<>(headers);

        URI builder = UriComponentsBuilder
                .fromHttpUrl(kakaoFoodInfoURi)
                .queryParam("category_group_code", "FD6")
                .queryParam("radius", 10000)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();



        //요청
        ResponseEntity<String> response = rt.exchange(
                kakaoFoodInfoURi,
                HttpMethod.GET,
                kakaoRequest,
                String.class
        );
        System.out.println("카카오 요청 응답: " + response);
        System.out.println("카카오 요청 바디: " + response.getBody());

        //JSON -> JAVA
        ObjectMapper mapper = new ObjectMapper();
        KakaoCategory category = null;
        try{
            category = mapper.readValue(response.getBody(), KakaoCategory.class);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
        //확인
        System.out.println("""
                [카카오 음식점 정보]
                place_name: %s
                address_name: %s
                phone: %s
                """.formatted(category.getPlaceName(), category.getAddressName(), category.getPhone()));



//        HttpResponse response = httpClient.execute(httpGet);
//        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
//
//        JSONObject jsonResponse = new JSONObject(responseBody);
//        JSONArray documents = jsonResponse.getJSONArray("documents");
//
//        for (int i = 0; i < documents.length(); i++) {
//            JSONObject place = documents.getJSONObject(i);
//
//            System.out.println("Name: " + place.getString("place_name"));
//            System.out.println("Address: " + place.getString("address_name"));
//            System.out.println("Tel: " + place.getString("phone"));
//            System.out.println("Latitude: " + place.getDouble("y"));
//            System.out.println("Longitude: " + place.getDouble("x"));

        return null;
    }

}
