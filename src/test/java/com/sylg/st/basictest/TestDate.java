package com.sylg.st.basictest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDate {
    @Test
    public void test1(){
        java.util.Date now = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");//可以方便地修改日期格式


        String hehe = dateFormat.format( now );
        System.out.println(hehe);
    }
    @Test
    public void test2(){

        java.util.Date now = new java.util.Date();
        System.out.println(now);
    }
    @Test
    public  void test3(){
        java.sql.Date date = java.sql.Date.valueOf("2018-3-12");
        System.out.println(date.toString());
        System.out.println(date.compareTo(java.sql.Date.valueOf("2018-03-11")));
        System.out.println(new Date(new java.util.Date().getTime()));
    }

}
