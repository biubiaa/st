package com.sylg.st.servicetest;

import com.sylg.st.mapper.TrainMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainTest {
    @Autowired
    TrainMapper trainMapper;
    @Test
    public void test1(){
        int id = trainMapper.getLatestTrainId();
        System.out.println(id);
    }
}
