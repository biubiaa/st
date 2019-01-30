package com.sylg.st.controllertest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class PasswordTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void deleteTrain() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/checkmemberpassword")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("memberId", "150305017").param("password","150050117")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void checkadminpassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/checkadminpassword")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("adminId", "admin").param("password","66666")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void addmemberpassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/addmemberpassword")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("memberId", "1503050115  ").param("password","1503050115")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
