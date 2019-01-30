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
//public class MoneyTest {
//    private MockMvc mvc;
//    @Autowired
//    private WebApplicationContext context;
//    @Before
//    public void setupMockMvc() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//    @Test
//    public void deletebill() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/deletebill")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("billid", "7")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void getbills() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getbills")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
////                .param("billid", "7")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void addbill() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/addbill")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("cost", "20").param("reason","买电池")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void addmoney() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getmoney")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
////                .param("cost", "20").param("reason","买电池")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//}
