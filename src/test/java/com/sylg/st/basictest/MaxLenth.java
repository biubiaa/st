package com.sylg.st.basictest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaxLenth {
    @Test
    public void show() {
        String string = "aaaaaaa";
        while(true)
        {
            string +=string;
            System.out.println(string.length());
        }

    }
}
