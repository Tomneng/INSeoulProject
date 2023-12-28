package com.inseoul.real_estate.service;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.domain.TbLnOpendataRentV;
import com.inseoul.real_estate.repository.HouseRepository;
import com.inseoul.real_estate.util.U;
import com.inseoul.user.domain.User;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.*;

import static java.lang.Long.parseLong;

@Service
public class HouseServiceImpl implements HouseService {

    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;


    private HouseRepository houseRepository;

    public HouseServiceImpl(SqlSession sqlSession) {
        houseRepository = sqlSession.getMapper(HouseRepository.class);
        System.out.println("Service 생성 완료");
    }


    @Override
    public int save(Row row) {
        return houseRepository.write(row);
    }
    //
    @Override
    public int moreThanOnce(Row row) {
        HttpSession session = U.getSession();
        if (row.getAccYear() == null) {
            row.setAccYear((String) session.getAttribute("accyear"));
            row.setSsgName((String) session.getAttribute("ssgname"));
            row.setDongCode((Integer) session.getAttribute("dongcode"));
            row.setHouseKindName((String) session.getAttribute("hkind"));
            row.setMbtiH((String) session.getAttribute("mbti"));
        } else {
            session.setAttribute("accyear", row.getAccYear());
            session.setAttribute("ssgname", row.getSsgName());
            session.setAttribute("dongcode", row.getDongCode());
            session.setAttribute("hkind", row.getHouseKindName());
            session.setAttribute("mbti", row.getMbtiH());
        }
        return houseRepository.countAll(row);
    }


    @Override
    public Row findById(Long id) {
        return houseRepository.selectById(id);
    }

    @Override
    public int putScore(Long houseId,Long userId, int contractScore, int placeScore) {
        if (houseRepository.checkScore(houseId, userId) == 0){
            houseRepository.initScore(houseId, userId, contractScore, placeScore);
            houseRepository.updateRealScore(houseId);
        }else {
            houseRepository.updateScore(houseId, userId, contractScore, placeScore);
            houseRepository.updateRealScore(houseId);
        }
        return 0;
    }


    /**
     * 기본적인 페이징에 조금 다른게
     * U.getSession()을 해서 session을 받아오면 검색조건이 있을때는 session의 attribute값을 넣어줌
     * -> 그래야 이후에 검색 인자값을 넘겨주지 않아도 session에서 그 값들을 빼서 사용할 수 있음
     * 기본적인 스타일이나 구성은 그냥 우리 게시판할때 페이징이랑 동일함(대신에 한번에 몇개 보여줄지 하는 기능은 그냥 뺐음)
     * + 이 기능을 morethanOnece()에서 해주기 때문에 아래에는 더이상 필요없음
     *
     * @param row   : 검색조건 담을 객체
     * @param page  : 페이지 번호
     * @param model
     * @return 검색조건에 맞는 객체들을 담은 list
     */
    @Override
    public List<Row> list(Row row, Integer page, Model model) {

        if (page == null) page = 1;  // 디폴트는 1page
        if (page < 1) page = 1;

        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
        HttpSession session = U.getSession();
        Integer writePages = (Integer) session.getAttribute("writePages");
        if (writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer) session.getAttribute("pageRows");
        if (pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        long cnt = houseRepository.countAllwithMbti(row);   //row를 매개변수로 전달해 필터링 된 글 목록 전체의 개수를 구함
        int totalPage = (int) Math.ceil(cnt / (double) pageRows);   // 총 몇 '페이지' ?

        // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지'
        int startPage = 0;
        int endPage = 0;

        // 해당 페이지의 글 목록
        List<Row> list = null;

        if (cnt > 0) {  // 데이터가 최소 1개 이상 있는 경우만 페이징
            //  page 값 보정
            if (page > totalPage) page = totalPage;

            // 몇번째 데이터부터 fromRow
            int fromRow = (page - 1) * pageRows;

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            // 해당페이지의 글 목록 읽어오기 + 필터링 인자들

            list = houseRepository.filteredSearch(row, fromRow, pageRows);
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


    /**
     * 실제로 db에 없는 값들은 그때그때마다 getapi를 써서 api 호출 및 DB에 저장
     * 여기는 그냥 controller에 있던 api호출 및 저장 기능을 함
     *
     * @param row2  : 넘겨받은 검색값
     * @param model : 얘는 사실 필요없을지도 모름
     * @param page  : 페이지값 넘겨주기인데 얘도 사실 필요없을지도
     */
    @Override
    public void getapi(Row row2, Model model, Integer page) {
        String url = String.format("http://openapi.seoul.go.kr:8088/%s/json/tbLnOpendataRentV/1/120/%s/%%20/%s/%d/%%20/%%20/%%20/%%20/%%20/%s"
                , "5146444173746f6d32346f53767a56", row2.getAccYear(), row2.getSsgName(), row2.getDongCode(), row2.getHouseKindName());

        RestTemplate restTemplate = new RestTemplate();
//        String rrr = restTemplate.getForObject(url, String.class);
//        System.out.println(rrr);

        ResponseEntity<Housedata> response = restTemplate.getForEntity(url, Housedata.class);
        Row row = null;

        if (response.getBody().getTbLnOpendataRentV() == null) {
            model.addAttribute("result", 0);
        } else {
            for (int i = 0; i < response.getBody().getTbLnOpendataRentV().getRow().size(); i++) {
                row = response.getBody().getTbLnOpendataRentV().getRow().get(i);

                // 보기 편한 주소값 column
                String address = "" + row.getSsgName() + " " + row.getDongName() + " " + row.getBobn() + "-" + row.getBubn();
                row.setAddress(address);
                houseRepository.write(row);
            }
        }
    }

    @Override
    public List<Integer> getAvgScores(Row row) {
        Integer pScore = houseRepository.avgPScore(row);
        Integer cScore =houseRepository.avgCScore(row);
        List<Integer> list = new ArrayList<>();
        list.add(row.getContractScore());
        list.add(cScore);
        list.add(row.getPlaceScore());
        list.add(pScore);
        return list;
    }

    @Override
    public Object[] getTop(Long houseId) {
        List<String> list1 = houseRepository.top3mbti(houseId);
        houseRepository.putTop1(list1.get(0) ,houseId);
        List<Integer> list2 = houseRepository.top3Prop(houseId);
        Object[] mixedArray = new Object[6];
        if (list1.isEmpty()){
            return mixedArray;
        } else if (list1.size() == 1 && list2.size() == 1) {

        mixedArray[0] = list1.get(0);
        mixedArray[1] = list2.get(0);
        return mixedArray;
        }else if (list1.size() == 2 && list2.size() == 2) {

        mixedArray[0] = list1.get(0);
        mixedArray[1] = list2.get(0);
        mixedArray[2] = list1.get(1);
        mixedArray[3] = list2.get(1);
        return mixedArray;
        }else if (list1.size() == 3 && list2.size() == 3){
            mixedArray[0] = list1.get(0);
            mixedArray[1] = list2.get(0);
            mixedArray[2] = list1.get(1);
            mixedArray[3] = list2.get(1);
            mixedArray[4] = list1.get(2);
            mixedArray[5] = list2.get(2);
            return mixedArray;
        }
        else {
            return null;
        }
    }

    @Override
    public List<Row> houseOnmain() {
        return houseRepository.houseOnmain();
    }

    @Override
    public void getOredered(Row row) {
        Integer all = houseRepository.countAll(row);
        if (all != 0){
            for (int i = 1; i <= all; i++) {
                Long num = Long.valueOf(i);
                List<String> list1 = houseRepository.top3mbti(num);
                if (list1.size() == 0){
                    houseRepository.putTop1("MBTI" ,num);
                }else {
                    houseRepository.putTop1(list1.get(0) ,num);
                }
            }
        }
    }

    @Override
    public List<Row> listDefault(Row row2, Integer page, Model model) {
        if (page == null) page = 1;  // 디폴트는 1page
        if (page < 1) page = 1;

        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
        HttpSession session = U.getSession();
        Integer writePages = (Integer) session.getAttribute("writePages");
        if (writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer) session.getAttribute("pageRows");
        if (pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        long cnt = houseRepository.countAll(row2);   //row를 매개변수로 전달해 필터링 된 글 목록 전체의 개수를 구함
        int totalPage = (int) Math.ceil(cnt / (double) pageRows);   // 총 몇 '페이지' ?

        // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지'
        int startPage = 0;
        int endPage = 0;

        // 해당 페이지의 글 목록
        List<Row> list = null;

        if (cnt > 0) {  // 데이터가 최소 1개 이상 있는 경우만 페이징
            //  page 값 보정
            if (page > totalPage) page = totalPage;

            // 몇번째 데이터부터 fromRow
            int fromRow = (page - 1) * pageRows;

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            // 해당페이지의 글 목록 읽어오기 + 필터링 인자들

            list = houseRepository.defaultSearch(row2, fromRow, pageRows);
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

}
