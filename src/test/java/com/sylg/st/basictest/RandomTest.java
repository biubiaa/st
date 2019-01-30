package com.sylg.st.basictest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomTest {
    @Test
    public void testRandom(){
        Random rand = new Random();
        System.out.println(rand.nextInt(100));
    }
}
