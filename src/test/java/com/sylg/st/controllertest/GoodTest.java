//package com.sylg.st.controllertest;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@WebAppConfiguration
//public class GoodTest {
//    private MockMvc mvc;
//    @Autowired
//    private WebApplicationContext context;
//    @Before
//    public void setupMockMvc() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//    @Test
//    public void test2() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/borrow")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("goodId", "1").param("memberId","1503050117").param("num","1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void test1() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/giveback")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("goodsrecordid", "20").param("num","1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void deletegood() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/deletegood")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("goodid", "2")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void deletegoodrecord() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/deletegoodrecord")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("goodrecordid", "1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void dgetgoods() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getgoods")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void getGoodPic() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/goodpic")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("path","src\\main\\resources\\static\\img\\20180312215730.jpg")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//}
