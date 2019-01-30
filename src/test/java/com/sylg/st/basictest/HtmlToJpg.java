package com.sylg.st.basictest;

import org.fit.cssbox.demo.ImageRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.SAXException;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HtmlToJpg {
    @Test
    public void test() throws IOException, SAXException {
        ImageRenderer render = new ImageRenderer();
        System.out.println("kaishi");
        String url = "http://localhost:63342/st/wordcloud/1.html";
        FileOutputStream out = new FileOutputStream(new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\1.png"));
        render.renderURL(url, out,ImageRenderer.Type.valueOf("PNG") );
        System.out.println("OK");
    }

}
