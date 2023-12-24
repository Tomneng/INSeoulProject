package com.inseoul.user.service;

import com.inseoul.food.repository.FoodRepository;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.repository.HouseRepository;
import com.inseoul.real_estate.util.U;
import com.inseoul.tour.domain.Item;
import com.inseoul.tour.domain.Tour;
import com.inseoul.tour.repository.TourRepository;
import com.inseoul.user.domain.ScrapQryResult;
import com.inseoul.user.domain.User;
import com.inseoul.user.domain.UserScraptedHouse;
import com.inseoul.user.domain.UserScraptedTour;
import com.inseoul.user.repository.UserRepository;
import com.inseoul.user.repository.UserScraptedRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

@Service
public class UserScraptedServiceImpl implements UserScraptedService {

    private HouseRepository houseRepository;
    private FoodRepository foodRepository;
    private UserScraptedRepository userScraptedRepository;

    private UserRepository userRepository;
    private TourRepository tourRepository;

    public UserScraptedServiceImpl(SqlSession sqlSession) {
        houseRepository = sqlSession.getMapper(HouseRepository.class);
        foodRepository = sqlSession.getMapper(FoodRepository.class);
        userScraptedRepository = sqlSession.getMapper(UserScraptedRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
        tourRepository = sqlSession.getMapper(TourRepository.class);
        System.out.println("Service 생성 완료");
    }

    @Override
    public List<Row> listById(Model model) {

        User user = U.getLoggedUser();

        Long userId = user.getUserId();

        List<Long> list = userScraptedRepository.getids(userId);
        List<Row> myrowlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            myrowlist.add(i,userScraptedRepository.selectScrapted(list.get(i)));
        }

        model.addAttribute("myrowlist", myrowlist);
        return myrowlist;
    }

    @Override
    public List<Item> listByIdTour(Model model) {
        User user = U.getLoggedUser();

        // 위 정보는 session 의 정보이고, 일단 DB 에서 다시 읽어온다
        Long userId = user.getUserId();

        List<Long> tourList = userScraptedRepository.getIdsTour(userId);
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < tourList.size(); i++){
            itemList.add(i,userScraptedRepository.selectScraptedTour(tourList.get(i)));
        }

        model.addAttribute("itemList", itemList);
        return itemList;
    }

    @Override
    public List<Item> noMbtiTourCard() {
        return userScraptedRepository.noMbtiTourCard();
    }


    @Override
    public List<Long> scraptedList(Long id) {
        List<Long> list = userScraptedRepository.getids(id);
        return list;
    }

    @Override
    public ScrapQryResult scrapted(Long userId, Long houseId) {
        if (userScraptedRepository.scrapCheck(userId, houseId) > 0){
            userScraptedRepository.deleteScrap(userId, houseId);
            ScrapQryResult result = ScrapQryResult.builder()
                    .count(1)
                    .status("DELETED")
                    .build();
            return result;
        }else {

        UserScraptedHouse userScraptedHouse = UserScraptedHouse.builder()
                .userId(userId)
                .houseId(houseId)
                .build();
        userScraptedRepository.addHouseScrapt(userScraptedHouse);
        ScrapQryResult result = ScrapQryResult.builder()
                .count(1)
                .status("OK")
                .build();
        return result;
        }
    }

    @Override
    public List<Long> scraptedTourList(Long userId) {
        List<Long> tourList = userScraptedRepository.getIdsTour(userId);
        return tourList;
    }

    @Override
    public ScrapQryResult scraptedTour(Long userId, Long tourId) {
        if (userScraptedRepository.scrapCheckTour(userId, tourId) > 0){
            userScraptedRepository.deleteScrapTour(userId, tourId);
            ScrapQryResult result = ScrapQryResult.builder()
                    .count(1)
                    .status("DELETED")
                    .build();
            return result;
        } else {
            UserScraptedTour userScraptedTour = UserScraptedTour.builder()
                    .userId(userId)
                    .tourId(tourId)
                    .build();
            userScraptedRepository.addTourScrapt(userScraptedTour);
            ScrapQryResult result = ScrapQryResult.builder()
                    .count(1)
                    .status("OK")
                    .build();
            return result;
        }
    }
}
