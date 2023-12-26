package com.inseoul.tour.service;

import com.inseoul.food.domain.FoodRow;
import com.inseoul.tour.domain.Item;
import com.inseoul.tour.domain.Tour;
import com.inseoul.tour.repository.TourRepository;
import com.inseoul.tour.util.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class TourServiceImpl implements TourService {

    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;

    private TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(SqlSession sqlSession){
        tourRepository = sqlSession.getMapper(TourRepository.class);
        System.out.println("Service 생성 완료");
    }
    @Override
    public int save(Item item) {
        return tourRepository.save(item);
    }

    @Override
    public Item findById(Long tourId) {
        return tourRepository.selectById(tourId);
    }

    // ↓ Session 에 넣는 이유는 '검색' 버튼을 누르지 않는 이상 페이지 이동 시에는
    // item2에 NULL 값이 들어가기 때문에 countAll에 0이 출력되게 됨
    // 그러면 Controller 에서 이미 DB 에 저장되어 있어도 다시 API 를 호출하는 불필요한 작업을 하게 되기 때문에
    // Session 에 값을 저장하여 사용함 → list 에 사용한 것도 같은 이유
//    @Override
//    public int search(Item item) {
//        HttpSession session = U.getSession();
//        if (item.getTourName() == null) {
//            item.setTourName((String) session.getAttribute("tourName"));
//            item.setTourSigungucode((String) session.getAttribute("tourSigungucode"));
//        } else {
//            session.setAttribute("tourName", item.getTourName());
//            session.setAttribute("tourSigungucode", item.getTourSigungucode());
//        }
//        return tourRepository.countAll(item);
////        return tourRepository.countAll();
//    }

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

            tourRepository.save(item);
        }
    }

    @Override
    public List<Item> list(Item item, Integer page, Model model) {
        if (page == null) page = 1; // 디폴트 1page
        if (page < 1) page = 1;
        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되는지
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인지
        HttpSession session = U.getSession();
        if (item.getTourName() == null) {
            item.setTourName((String) session.getAttribute("tourName"));
            item.setTourSigungucode((String) session.getAttribute("tourSigungucode"));
        } else {
            session.setAttribute("tourName", item.getTourName());
            session.setAttribute("tourSigungucode", item.getTourSigungucode());
        }

        Integer writePages = (Integer) session.getAttribute("writePages");
        if (writePages == null) writePages = WRITE_PAGES;   // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer) session.getAttribute("pageRows");
        if (pageRows == null) pageRows = PAGE_ROWS;     // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        long cnt = tourRepository.countAll(item);   // 글 목록 전체의 개수
//        long cnt = tourRepository.countAll();   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);   // 총 몇 '페이지'

        // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지'
        int startPage = 0;
        int endPage = 0;

        // 해당 페이지의 글 목록
        List<Item> list = null;

        if(cnt > 0){  // 데이터가 최소 1개 이상 있는 경우만 페이징
            //  page 값 보정
            if(page > totalPage) page = totalPage;

            // 몇번째 데이터부터 fromRow
            int fromRow = (page - 1) * pageRows;

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            // 해당페이지의 글 목록 읽어오기 + 필터링 인자들
            list = tourRepository.selectFromRow(item.getTourSigungucode(), item.getTourName(), fromRow, pageRows);
//            list = tourRepository.selectFromRow(fromRow, pageRows);
            model.addAttribute("list", list);
        } else {
            page = 0;
        }

            model.addAttribute("cnt", cnt);  // 전체 글 개수
            model.addAttribute("page", page); // 현재 페이지
            model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
            model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

            // [페이징]
            model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
            model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
            model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
            model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        return list;
    }


    @Override
    public List<String> foodList(String[] list) {
        List<String> b = new ArrayList<>();
        List<String> a = tourRepository.selectByFood();
        for (int i = 0; i < list.length; i++){
            if (a.contains(list[i])) {
                b.add(list[i]);
            }
        }
        return b;
    }

    @Override
    public Long findByFoodId(String storeTel) {
        return tourRepository.selectByFoodId(storeTel);
    }
}