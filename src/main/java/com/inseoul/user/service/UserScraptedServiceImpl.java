package com.inseoul.user.service;

import com.inseoul.food.repository.FoodRepository;
import com.inseoul.real_estate.domain.Row;
import com.inseoul.real_estate.repository.HouseRepository;
import com.inseoul.user.domain.ScrapQryResult;
import com.inseoul.user.domain.User;
import com.inseoul.user.domain.UserScraptedHouse;
import com.inseoul.user.repository.UserRepository;
import com.inseoul.user.repository.UserScraptedRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class UserScraptedServiceImpl implements UserScraptedService {

    private HouseRepository houseRepository;
    private FoodRepository foodRepository;
    private UserScraptedRepository userScraptedRepository;

    private UserRepository userRepository;
//    private TourRepository tourRepository;

    public UserScraptedServiceImpl(SqlSession sqlSession) {
        houseRepository = sqlSession.getMapper(HouseRepository.class);
        foodRepository = sqlSession.getMapper(FoodRepository.class);
        userScraptedRepository = sqlSession.getMapper(UserScraptedRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
//        tourRepository = sqlSession.getMapper(TourRepository.class);
        System.out.println("Service 생성 완료");
    }

    @Override
    public List<Row> listById(Long userId, Model model) {

        List<Long> list = userScraptedRepository.getids(userId);
        List<Row> rowlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            rowlist.add(i,userScraptedRepository.selectScrapted(list.get(i)));
        }

        model.addAttribute("rowlist", rowlist);
        return rowlist;
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
}