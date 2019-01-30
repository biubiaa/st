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
//public class TrainTest {
//    private MockMvc mvc;
//    @Autowired
//    private WebApplicationContext context;
//    @Before
//    public void setupMockMvc() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void addTrain() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/addtrain")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("trainName", "电协第二次培训")
//                .param("trainTime","2018年3月17日上午十点")
//                .param("trainRoom","综合楼208")
//                .param("trainExplain","这是本学期电协第二次进行大规模培训设计的内容如下。。。。。。。。。。。。。。。。。。。。。。。。" +
//                        "。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。" )
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    public void deleteTrain() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/deletetrain")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("trainId", "1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    public void getTrains() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/gettrainslist")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    public void getTrain() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/gettrain")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("trainId","4")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//}
