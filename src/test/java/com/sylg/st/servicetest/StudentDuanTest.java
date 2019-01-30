//package com.sylg.st.servicetest;
//
//import com.sylg.st.dto.ComList;
//import com.sylg.st.service.impl.MacServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class StudentDuanTest {
//    @Autowired
//    MacServiceImpl macService;
//    @Test
//    public  void test1(){
//        List<ComList> comLists  = macService.getStudentXianshiList();
//        for (ComList c
//                : comLists
//             ) {
//            System.out.println(c.getId()+","+c.getName()+","+c.getType()+","+c.getFabuTime());
//        }
//    }
//}
