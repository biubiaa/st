package com.sylg.st.basictest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WriteTxt {
    @Test
    public void writeTxt() throws IOException {

        File file = new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\h.txt");
        OutputStreamWriter osm = new OutputStreamWriter(new FileOutputStream(file));
        BufferedWriter bw = new BufferedWriter(osm);
        bw.write("hhhhhhhhhhhhhhhhhhhhha");
        bw.close();
    }
}
