package com.sylg.st.mactest;

import org.junit.Test;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacDsTime {
    @Test
public void testDs() throws IOException {

    File file = new File("C:\\Users\\master\\Desktop\\backup_data.dat");
    BufferedReader bf  = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    List<String> strings = new ArrayList<>();
//        System.out.println(bf.readLine());
    String str;
    String reg="([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}";
    String reg2 = "ds";
    int count=0;
    while((str=bf.readLine())!=null){
        Pattern p1 = Pattern.compile(reg2);
        Matcher m1 = p1.matcher(str);
        while (m1.find()){
            count++;
        }
        Pattern p= Pattern.compile(reg);
        Matcher m=p.matcher(str);
        while(m.find()){
            strings.add(m.group());

        }}

    System.out.println(strings.size());
    System.out.println(count);
        NumberFormat numberFormat = NumberFormat.getInstance();

        // 设置精确到小数点后2位

        numberFormat.setMaximumFractionDigits(2);

        String result = numberFormat.format((float) count/ (float) strings.size() * 1000);
        System.out.println(result);
}

}