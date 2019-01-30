//package com.sylg.st.controllertest;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sylg.st.pojo.Member;
//import com.sylg.st.service.MemberService;
//import org.hamcrest.Matchers;
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
//public class MemberTest {
//    private MockMvc mvc;
//    @Autowired
//    private WebApplicationContext context;
//    @Autowired
//    MemberService memberService;
//
//    @Before
//    public void setupMockMvc() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//
//    @Test
//    public void getMembers() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getallmembers")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
////                .param("id", "1503050117")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void getMemberById() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getmemberbyid")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("id", "1503050117")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void getThs() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getths")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    public void getMemberByThs() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getthmember")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("th","15")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    public void getAllMembers() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getallmembers")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    public void addMember() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/addmember")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("memberId","1503050114").param("memberName","信勇")
//                .param("memberSex","女").param("schoolName","信息科学与工程学院")
//                .param("departmentName","技术部").param("memberQq","1111111")
//                .param("memberWechat","2929292").param("memberPhone","13194237676")
//                .param("memberIdentify","部员").param("memberTh","15")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//@Test
//public void deleteMember() throws Exception {
//    mvc.perform(MockMvcRequestBuilders.post("/deletemember")
//            .contentType(MediaType.APPLICATION_JSON_UTF8)
//            .param("id","1503050117")
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andDo(MockMvcResultHandlers.print());
//
//}
//    @Test
//    public void updatemember() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/modifymember")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("memberId","1503050114").param("memberName","信勇")
//                .param("memberSex","男").param("schoolName","信息科学与工程学院")
//                .param("departmentName","技术部").param("memberQq","1111111")
//                .param("memberWechat","2929292").param("memberPhone","13194237676")
//                .param("memberIdentify","部员").param("memberTh","15")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    public void getZhaoxin () throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/templet1")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//}
