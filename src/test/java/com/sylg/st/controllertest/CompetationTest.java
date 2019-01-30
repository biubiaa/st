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
//public class CompetationTest {
//    private MockMvc mvc;
//    @Autowired
//    private WebApplicationContext context;
//    @Before
//    public void setupMockMvc() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }    @Test
//    public void test2() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/addcompetation")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .param("comName","移动应用大赛").param("comTime","2018年3月20日上午10点")
//                .param("explain","此次比赛针对沈阳理工大学举办，意在突出。。" +
//                        "。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。" +
//                        "。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testdelete() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/deletecompetation")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .param("comId","2")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testgetComList() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getallcompetations")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testgetCom() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/getcommessage")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("comId","1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testStartApply() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/startapply")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("comId","1").param("comCapacity","2").param("teamName","1")
//                .param("teamKouhao","1").param("memberId","1").param("memberName","1")
//                .param("memberSchool","1").param("memberQQ","1").param("memberPhone","1")
//                .param("comType","1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testCloseTest() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/closeapply")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("comId","1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testCCommembers() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/commembers")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("comId","1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testApply() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/apply")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("comId","1").param("captain","1503050117").param("comType","微电影组")
//                .param("teamName","嘻嘻哈哈队").param("teamKouhao","嘻嘻哈哈乐翻天")
//                .param("member1Id","1503050117").param("member1Name","徐天豪").param("member1School","信息科学与工程学院")
//                .param("member1QQ","1101375808").param("member1Phone","13194238162")
//                .param("member2Id","1503050116").param("member2Name","李泽伟").param("member22School","信息科学与工程学院")
//                .param("member2QQ","11010110").param("member2Phone","13194238162")
//                .param("member3Id","1503050115").param("member3Name","岳贤哲").param("member3School","信息科学与工程学院")
//                .param("member3QQ","447824").param("member3Phone","13194238162")
//                .param("member4Id","1503050114").param("member4Name","信勇").param("member4School","信息科学与工程学院")
//                .param("member4QQ","367236").param("member4Phone","13194238162")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testCancelApply() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/cancelapply")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("comId","1").param("memberId","1503050117")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testSomeoneCom() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/someonecom")
//                .contentType(MediaType.APPLICATION_JSON_UTF8).param("memberId","1503050116")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//    @Test
//    public void testAbCom() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/abcom")
//                .contentType(MediaType.APPLICATION_JSON_UTF8
//                ).param("memberId","1503050116")
//                .param("comId","1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
////                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
//
//    }
//}
