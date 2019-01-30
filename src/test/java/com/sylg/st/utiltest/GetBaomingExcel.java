package com.sylg.st.utiltest;

import com.sylg.st.mapper.ComApplicationMapper;
import com.sylg.st.mapper.CompetationMapper;
import com.sylg.st.pojo.ComApplication;
import com.sylg.st.pojo.Competation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetBaomingExcel {
    @Autowired
    ComApplicationMapper comApplicationMapper;
    @Autowired
    CompetationMapper competationMapper;
    @Test
    public void test() throws IOException {
        int comId = 1;
        List<ComApplication> comApplications = comApplicationMapper.selectByComId(comId);
        System.out.println(comApplications.get(0).getTeacher());
//        Competation c = competationMapper.selectByPrimaryKey(comId);
//        System.out.println(c.getCompetationName());
//        try {
            System.out.println(com.sylg.st.util.GetBaomingExcel.getBaomingExcel("计算机设计大赛", comApplications));
//        }catch (Exception e){
//            System.out.println(e.getClass()+":"+e.getMessage());
//        }
    }
}
