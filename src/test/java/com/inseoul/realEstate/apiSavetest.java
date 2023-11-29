package com.inseoul.realEstate;

import com.inseoul.real_estate.domain.Housedata;
import com.inseoul.real_estate.repository.HouseRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class apiSavetest {
    @Autowired
    private SqlSession sqlSession;
    @Test
    void test1(){
        HouseRepository houseRepository = sqlSession.getMapper(HouseRepository.class);

        Housedata housedata = Housedata.builder()
                .accYear("2023")
                .ssgCode(11230)
                .ssgName("동대문구")
                .dongCode(10700)
                .dongName("청량리동")
                .contractDate("20231123")
                .rentKind("전세")
                .rentArea(31.37)
                .rentDeposit(12000)
                .rentFee(0)
                .buildYear(1987)
                .houseKindName("단독다가구")
                .contractPeriod("24.01~26.01")
                .newRonSecd("갱신")
                .build();
        int cnt = houseRepository.write(housedata);
        System.out.println("make() 결과 = " + cnt);
    }

}
