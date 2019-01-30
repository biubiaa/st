//package com.sylg.st.servicetest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sylg.st.pojo.StudentTest;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class qiandaoTest {
//    @Test
//    public void show(){
//        String str="e\":\"Tue Feb 21 08:13:31 2017\",\"lat\":\"30.747988\",\"lon\":\"103.973152\" ," +
//                "\"student\": [{\"mac\":\"a4:56:02:61:7f:57\",\"rssi\":\"-91\",\"range\":\"91.5\"}," +
//                "{\"mac\":\"8c:a6:df:62:2d:3d\",\"rssi\":\"-93\",\"range\":\"108.5\"}," +
//                "{\"mac\":\"a4:56:02:71:be:b3 \",\"rssi\":\"-96\",\"range\":\"140.1\"}," +
//                "{\"mac\":\"cc:34:29:97:4d:0d\",\"rssi\":\"-95\",\"range\":\"128.6\"}," +
//                "{\"mac\":\"44:33:4c:aa:71:82\",\"rssi\":\"-94\",\"range\":\"11\n" +
//                "8.1\"},{\"mac\":\"b0:48:7a:5a:10:f8\",\"rssi\":\"-86\",\"range\":\"59.7\"}," +
//                "{\"mac\":\"a8:57:4e:9d:ca:d8\",\"rssi\":\"-96\",\"range\":\"140.1\"}," +
//                "{\"mac\":\"5e:cf:7f:93:3 d:0e\",\"rssi\":\"-56\",\"range\":\"4.6\"}," +
//                "{\"mac\":\"5e:cf:7f:93:3d:0f\",\"rssi\":\"-58\",\"range\":\"5.5\"}," +
//                "{\"mac\":\"5e:cf:7f:93:3d:10\",\"rssi\":\"-63\",\"range\":\"8. 4\"}," +
//                "{\"mac\":\"5e:cf:7f:93:3d:0b\",\"rssi\":\"-68\",\"range\":\"12.9\"}," +
//                "{\"mac\":\"5e:cf:7f:93:3d:0c\",\"rssi\":\"-53\",\"range\":\"3.5\"}," +
//                "{\"mac\":\"5e:cf:7f:93:3d:0d\n" +
//                "\",\"rssi\":\"-69\",\"range\":\"14.0\"}," +
//                "{\"mac\":\"e4:f3:f5:24:2c:d8\",\"rssi\":\"-89\",\"range\":\"77.1\"}," +
//                "{\"mac\":\"14:cf:92:8a:8f:f0\",\"rssi\":\"-96\",\"range\":\"14 0. 1\"}]}14:cf:92:8a:8f:f0," +
//                "14:cf:92:8a:8f:f0,14:cf:92:8a:8f:f0,14:cf:92:8a:8f:f0,14:cf:92:8a:8f:f0" +
//                ",14:cf:92:8a:8f:f0,14:cf:92:8a:8f:f0,14:cf:92:8a:8f:f0,14:cf:92:8a:8f:f0" +
//                ",14:cf:92:8a:8f:f0,14:cf:92:8a:8f:f0,14:cf:92:8a:8f:f0,14:cf:92:8a:8f:f0";
//        String reg="([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}";
//        Pattern p= Pattern.compile(reg);
//        Matcher m=p.matcher(str);
//        ArrayList<String> list=new ArrayList<>();
//
//
//        while(m.find()){
//            //System.out.println(m.group());
//            // System.out.println(m.start()+"...."+m.end());//找到每个字符的角标    }
//            list.add(m.group());
//        }
//        List<String> a = list.stream().distinct().collect(Collectors.toList());
//        for (String s:
//a           ) {
//            System.out.println(s);
//        }
//    }
//    @Test
//    public  void Test2() throws IOException {
//        File file = new File("C:\\Users\\天豪\\Desktop\\backup_data.dat");
//        BufferedReader bf  = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//            List<String> strings = new ArrayList<>();
//            String str;
//            while((str=bf.readLine())!=null);
//            String reg="rssi";
//            Pattern p= Pattern.compile(reg);
//            Matcher m=p.matcher(str);
//            while(m.find()){
//                //System.out.println(m.group());
//                // System.out.println(m.start()+"...."+m.end());//找到每个字符的角标    }
//                strings.add(m.group());
//
//        }
//        System.out.println(strings.size());
////        List<String> a = strings.stream().distinct().collect(Collectors.toList());
////        a = strings.stream().distinct().collect(Collectors.toList());
////        a = strings.stream().distinct().collect(Collectors.toList());
//        for(int i = 0;i<strings.size();i++){
////            System.out.println(strings.get(i));
//            for (int j = i+1;j<strings.size();j++){
//                if(strings.get(i).substring(0,2).equals(strings.get(j).substring(0,2)))
//                    strings.remove(j);
//            }
//        }
//        for(int i = 0;i<strings.size();i++){
////            System.out.println(strings.get(i));
//            for (int j = i+1;j<strings.size();j++){
//                if(strings.get(i).substring(0,2).equals(strings.get(j).substring(0,2)))
//                    strings.remove(j);
//            }
//        }
////        List<String> a = strings.stream().distinct().collect(Collectors.toList());
////        System.out.println(a.size());
//        for (String s: strings
//             ) {
//            System.out.println(s);
//        }
//        System.out.println(strings.size());
////        System.out.println(strings.size());
//    }
//}
