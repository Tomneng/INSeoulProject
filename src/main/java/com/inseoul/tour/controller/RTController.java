package com.inseoul.tour.controller;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.inseoul.tour.domain.Item;
import com.inseoul.tour.domain.Tour;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@RestController
@RequestMapping("/tour")
public class RTController {

    @GetMapping("/tourList")
    public Tour tour() throws IOException {

        String apiUrl = "https://apis.data.go.kr/B551011/KorService1/searchKeyword1";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("ServiceKey", "C1kTT5SDomuASfikyoi68DoMygPZN35TX4oq5AsfuOvznPN2X3cciPhyU5HObfGBz7tyl83nmm+a986uutA3yw==")
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "Inseoul")
                .queryParam("areaCode", "1")
                .queryParam("contentTypeId", "12")
                .queryParam("numOfRows", "12")
                .queryParam("sigunguCode", "23")
                .queryParam("keyword", "강남")
                .queryParam("_type", "json");
        // 수동으로 인코딩하지 않고 그대로 URI를 사용
        String assembledUri = builder.build(true).toUriString();
        System.out.println(assembledUri);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.TEXT_XML));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tour> response = restTemplate.exchange(assembledUri, HttpMethod.GET, entity, Tour.class);

        System.out.println(response.toString());




        return null;
//        String serviceKey = "C1kTT5SDomuASfikyoi68DoMygPZN35TX4oq5AsfuOvznPN2X3cciPhyU5HObfGBz7tyl83nmm%2Ba986uutA3yw%3D%3D";
//        String MobileOS = "ETC";
//        String MobileApp = "Inseoul";
//        String areaCode = "1";
//        String contentTypeId = "12";
//        String numOfRows = "12";
//        String sigunguCode = "23";  // 입력할 수 있도록 변경
//        String keyword = "광화문";     // 입력할 수 있도록 변경
//        String _type = "json";
//
//        StringBuilder urlBuilder = new StringBuilder(apiUrl);
//        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
//        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode(MobileOS, "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode(MobileApp, "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode(areaCode, "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode(contentTypeId, "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("sigunguCode", "UTF-8") + "=" + URLEncoder.encode(sigunguCode, "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode(_type, "UTF-8"));

//        String url = urlBuilder.toString();


//        System.out.println(item.getImage());
//        System.out.println(item.getTourId());


//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  // OpenConnection() 메서드는 오류가 발생하면 IOException 을 발생시킴
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
////         API 응답 코드가 성공적인 응답인 경우 InputStream / 그렇지 않을 경우 ErrorStream
//        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//
//        // API 응답 내용을 StringBuilder에 저장
//        StringBuilder sb = new StringBuilder();
//        // BufferedReader 를 사용하여 API 응답 내용을 한 줄씩 읽음
//        String line;
//        while ((line = rd.readLine()) != null) {
//            //
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//
//        // API 응답 내용을 문자열로 반환
//        String result = sb.toString();
//        System.out.println(result);
//
//    return result;
//        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
//            String line;
//            while ((line = rd.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
//        conn.disconnect();
//        return "";

    }
}
