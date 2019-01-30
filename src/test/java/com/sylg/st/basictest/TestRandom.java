package com.sylg.st.basictest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRandom
{
    @Test
    public void testRandom(){
        Random random = new Random();
        for (int i =0 ;i<=20;i++){
            System.out.println(random.nextDouble()*5);
        }
    }
}
