//package com.sylg.st.servicetest;
//
//import com.sylg.st.service.impl.AnalysisImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AnaLisisTest {
//    @Autowired
//    AnalysisImpl analysis;
//    @Test
//    public void testwordcloud2(){
//        System.out.println(analysis.getwordCloud(1));
//    }
//    @Test
//    public void test2() throws IOException {
//        File file = new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\h.xml");
//        OutputStreamWriter osm = new OutputStreamWriter(new FileOutputStream(file));
//        BufferedWriter bw = new BufferedWriter(osm);
//        bw.write("dagbuygfdsbfwqybdfuwqyfbuywqsabfiwuyfbvyu");
//        bw.close();
//    }
//}
