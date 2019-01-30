package com.sylg.st.mappertest;

import com.sylg.st.mapper.MembermacMapper;
import com.sylg.st.pojo.Membermac;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberMacTest {
    @Autowired
    MembermacMapper membermacMapper;
    @Test
    public void test(){

        List<Membermac> membermacs=membermacMapper.selectByTh(15);
        for (Membermac m :
                membermacs) {
            System.out.println(m.getMac());
        }
    }
    @Test
    public  void testGetTh(){
        List<Integer> ths = membermacMapper.ths();
        for (Integer i: ths
             ) {
            System.out.println(i);

        }
    }
}
