package com.sylg.st.basictest;


import com.sylg.st.pojo.Member;
import com.sylg.st.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringUtilTest{
    @Test
    public void test(){
        List<Member> members = new ArrayList<Member>();
        for(int i = 0;i<3;i++) {
            Member member = (Member) SpringUtil.getBean("Member");
            member.setMemberId(1);
            members.add(member);
        }
        for (Member mem :
                members) {
            System.out.println(mem.getMemberId());
        }
    }
}
