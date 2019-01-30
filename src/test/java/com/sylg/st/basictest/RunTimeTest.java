package com.sylg.st.basictest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunTimeTest {
    @Test
    public void testRunTime(){
        int testNum;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0;i<10000000;i++){
            arrayList.add(i);
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0;i<10000000;i++){
            linkedList.add(i);
        }
        long arrayTime1 = System.currentTimeMillis();
        for (Integer i: arrayList
             ) {
            testNum = i;
        }
        long arrayTime2 = System.currentTimeMillis();
        System.out.println(arrayTime2-arrayTime1);
        long linkTime1 = System.currentTimeMillis();
        for (Integer i: linkedList
             ) {
            testNum = i;
        }
        long linkTime2 = System.currentTimeMillis();
        System.out.println(linkTime2-linkTime1);
    }
}
