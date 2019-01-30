package com.sylg.st.servicetest;

import com.sylg.st.mapper.CommentMapper;
import com.sylg.st.pojo.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {
    @Autowired
    CommentMapper commentMapper;
    @Test
    public void test1(){
        List<Comment> comments = commentMapper.selectByComId(1);
        System.out.println(comments.size());
    }
}
