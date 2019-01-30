package com.sylg.st.basictest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeFileName {
    @Test
    public void test1(){
        File file = new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\testfile\\1.html");
        file.renameTo(new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\testfile\\hahha.html"));
        System.out.println(file.exists());
    }

}
