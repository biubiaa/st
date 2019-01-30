package com.sylg.st.basictest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateCompare {
    @Test
    public void test() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String jiezhibaoming = "2018-04-29";
        String startbaoming = "2018-04-10";
        String now = df.format(new Date());
        Boolean i = df.parse(jiezhibaoming).compareTo(new Date())== new Date().compareTo(df.parse(startbaoming));
        System.out.println(df.parse(jiezhibaoming).compareTo(new Date()));
        System.out.println(df.parse(jiezhibaoming).compareTo(df.parse(now)));
        System.out.println(new Date().compareTo(df.parse(startbaoming)));
        System.out.println(df.parse(jiezhibaoming).compareTo(df.parse(startbaoming)));
        System.out.println(i);
    }
}
