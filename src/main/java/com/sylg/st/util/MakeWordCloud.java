//package com.sylg.st.util;
//
//import org.apache.commons.io.FileUtils;
//import org.rosuda.REngine.REXP;
//import org.rosuda.REngine.Rserve.RConnection;
//
//import java.io.*;
//
//public class MakeWordCloud {
//    public static void makeWordCloud(String comments,int trainId){
//        try {
//            File file = new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\h.txt");
//            OutputStreamWriter osm = new OutputStreamWriter(new FileOutputStream(file));
//            BufferedWriter bw = new BufferedWriter(osm);
//            bw.write(comments);
//            bw.close();
//            System.out.println(comments.length());
//            FileUtils.writeLines(new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\h.txt")
//                    ,"GBK",
//                    FileUtils.readLines(new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\h.txt"),
//                            "UTF-8"));
//            RConnection c = new RConnection();
//            REXP wk = c.eval("source(\"F:\\\\JavaCode\\\\idea_code\\\\st\\\\src\\\\main\\\\resources\\\\wordcloud\\\\1.R\")");
//            file.delete();
//            File file2 = new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\h.html");
//            file2.renameTo(new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\"+trainId+".html"));
//        }catch (Exception e){
//
//        }finally {
//            File file1 = new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\wordcloud\\h.txt");
//            file1.delete();
//        }
//    }
//}
