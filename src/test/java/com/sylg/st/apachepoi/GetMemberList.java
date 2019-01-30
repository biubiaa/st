package com.sylg.st.apachepoi;

import com.sylg.st.mapper.MemberMapper;
import com.sylg.st.pojo.Member;
import com.sylg.st.util.GetMemberListExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetMemberList {
    @Autowired
    MemberMapper memberMapper;
    @Test
    public void test() throws IOException {
        List<Member> members = memberMapper.getMembersByTh(2015);
        System.out.println(members.size());
        String path = GetMemberListExcel.getMemberListExcel(String.valueOf(2015),members);
        System.out.println(path);
    }

}
