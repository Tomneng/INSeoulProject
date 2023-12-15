package com.inseoul.tour.service;

import com.inseoul.tour.domain.Item;
import com.inseoul.tour.domain.Tour;
import com.inseoul.tour.repository.TourApiRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
public class TourApiServiceImpl implements TourApiService {

    private TourApiRepository tourApiRepository;

    @Autowired
    public TourApiServiceImpl(SqlSession sqlSession) {
        tourApiRepository = sqlSession.getMapper(TourApiRepository.class);
        System.out.println("Service() 생성 완료");
    }
    @Override
    public void getTourApi(Item item2) {
            String apiUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("serviceKey", "J8PrTBaAdSOCeLnyBjw9bmy/fwolJYN7gzspJ4V1lDdkcXkn91IE/Y2OZrFhewei/iwdJBssG/LCMGhnoRMnSA==")
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "Inseoul")
                    .queryParam("areaCode", "1")
                    .queryParam("contentTypeId", "12")
                    .queryParam("arrange", "O")
                    .queryParam("numOfRows", "764")
                    .queryParam("_type", "json");
            // 수동으로 인코딩하지 않고 그대로 URI를 사용
            URI assembledUri = builder.build().encode().toUri();
            System.out.println(assembledUri);


            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.TEXT_XML));
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Tour> response = restTemplate.getForEntity(assembledUri, Tour.class);

            Item item = null;

            for (int i = 0; i < response.getBody().getResponse().getBody().getItems().getItem().size(); i++) {
                item = response.getBody().getResponse().getBody().getItems().getItem().get(i);

                tourApiRepository.save(item);
            }
        }
}
