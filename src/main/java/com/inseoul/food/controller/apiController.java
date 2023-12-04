//package com.inseoul.food.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.xml.stream.Location;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//
//@RestController
//@RequestMapping("/food")
//public class apiController {
//    @GetMapping("/food")
//    public String getKakaoApiFromAddress(String roadFullAddr) throws IOException {
//        String apiKey = "61751b5d791d1ce89ad7cb69233f3ee0";
//        String apiUrl = "https://dapi.kakao.com/v2/local/search/category.json";
////        String jsonString = null;
//
//        try {
//            roadFullAddr = URLEncoder.encode(roadFullAddr, "UTF-8");
//
//            String addr = apiUrl + "?category_group_code=FD6&radius=10000";
//
//            URL url = new URL(addr);
//            URLConnection conn = url.openConnection();
////            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);
//
//            BufferedReader rd = null;
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            StringBuffer docJson = new StringBuffer();
//
//            String line;
//
//            while((line = rd.readLine()) != null){
//                docJson.append(line);
//            }
//            jsonString = docJson.toString();
//            rd.close();
//
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return jsonString;
//    }
//
//}
