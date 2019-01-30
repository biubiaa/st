package com.sylg.st.basictest;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeTxtEncoding {
    @Test
    public void test1() throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\master\\Desktop\\test.txt")));
        PrintWriter pw = new PrintWriter(new FileOutputStream("C:\\Users\\master\\Desktop\\test.txt"));
        String str = null;
        while((str = buf.readLine()) != null) {
            String outStr = new String(str.getBytes("UTF-8"), "GBK");
            pw.write(outStr);
        }
    }
    @Test
    public void test2() throws IOException {
        FileUtils.writeLines(new File("C:\\Users\\master\\Desktop\\test.txt")
                ,"UTF-8",
                FileUtils.readLines(new File("C:\\Users\\master\\Desktop\\test.txt"),
                        "GBK"));
    }
}
