package com.sylg.st.web;

import com.sylg.st.dto.ComList;
import com.sylg.st.mapper.MembermacMapper;
import com.sylg.st.mapper.PlayphoneMapper;
import com.sylg.st.pojo.Membermac;
import com.sylg.st.pojo.Playphone;
import com.sylg.st.service.impl.AnalysisImpl;
import com.sylg.st.service.impl.MacServiceImpl;
import com.sylg.st.util.JsonResult;
import com.sylg.st.util.SpringUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class MacController {
    @Autowired
    MacServiceImpl macService;
    @Autowired
    PlayphoneMapper playphone;
    @Autowired
    AnalysisImpl analysis;

    @RequestMapping(value = "addmac", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addMac(@RequestParam Integer memberId, @RequestParam String mac) {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            int order = macService.addMac(memberId, mac);
            if (order > 0) {
                result.setStatus("ok");
                result.setResult(order);
            } else {
                result.setStatus("fail");
                result.setResult(order);
            }
        } catch (Exception e) {
            result.setStatus("error");
            result.setResult(e.getClass() + ":" + e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "updatemac", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> updateMac(@RequestParam Integer memberId, @RequestParam String mac) {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            int order = macService.updateMac(memberId, mac);
            if (order > 0) {
                result.setStatus("ok");
                result.setResult(order);
            } else {
                result.setStatus("fail");
                result.setResult(order);
            }
        } catch (Exception e) {
            result.setStatus("error");
            result.setResult(e.getClass() + ":" + e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 获取某个人的mac
     * */
    @RequestMapping(value = "getmacbymemberid",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getMacByMemberId(@RequestParam Integer memberId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            Membermac membermac = macService.getMembermac(memberId);
            if (membermac != null) {
                result.setResult(membermac);
                result.setStatus("ok");
            } else {
                result.setStatus("ok");
                result.setResult("");
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 自动签到及统计rssi占比
     */
    @RequestMapping(value = "qiandaoauto", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> qianDao(@RequestParam MultipartFile file) {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        String reg = "([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}";
        String reg_playphone = "ds";
        Pattern p = Pattern.compile(reg);
        Pattern p_playphone = Pattern.compile(reg_playphone);
        List<String> data = new ArrayList<>();
        int allCount = 0;
        int rssiCount = 0;
        try {
            byte[] macfiles = file.getBytes();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = df.format(new Date()) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File file1 = new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\static\\qiandao\\" + filename);
            FileOutputStream outputStream = new FileOutputStream(file1);
            outputStream.write(macfiles);
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
            data = FileUtils.readLines(new File(file1.getAbsolutePath()), "GBK");
            ArrayList<String> list = new ArrayList<>();
            for (String str : data
                    ) {
                Matcher m = p.matcher(str);
                while (m.find()) {
                    list.add(m.group());
                }
            }
            allCount = list.size();
            for (String str : data
                    ) {
                Matcher m1 = p_playphone.matcher(str);
                while (m1.find()) {
                    rssiCount++;
                }
            }
            macService.playPhone(rssiCount, allCount);
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).substring(0, 4).equals(list.get(j).substring(0, 4)))
                        list.remove(j);
                }
            }

            int order = macService.qiandao(list);
            if (order > 0) {
                result.setStatus("ok");
                result.setResult(order);
            } else {
                result.setStatus("fail");
                result.setResult(order);
            }
        } catch (Exception e) {
            result.setResult(e.getClass() + ":" + e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 获得某次活动rssi占比
     */
    @RequestMapping(value = "getrssi", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getRssi(@RequestParam Integer trainId) {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            Playphone playphone1 = playphone.selectBytrainId(trainId);
            if (playphone1 != null) {
                result.setStatus("ok");
                result.setResult(playphone1);
            } else {
                result.setStatus("fail");
                result.setResult(playphone1);
            }
        } catch (Exception e) {
            result.setStatus("error");
            result.setResult(e.getClass() + ":" + e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 获取rssi占比列表
     * */
    @RequestMapping(value = "getrssis",method = RequestMethod.GET)
    public  ResponseEntity<JsonResult> getRssis(){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<Playphone> playphones = playphone.selectAll();
            if(playphones!=null){
                result.setStatus("ok");
                result.setResult(playphones);
            }else {
                result.setStatus("fail");
                result.setResult(playphones);
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 获取通知
     */
    @RequestMapping(value = "tongzhilist", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getTongzhiList() {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            List<ComList> tongzhiList = macService.getStudentXianshiList();
            result.setStatus("ok");
            result.setResult(tongzhiList);
        } catch (Exception e) {
            result.setStatus("error");
            result.setResult(e.getClass() + ":" + e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 获取词云
//     * */
//    @RequestMapping(value = "getciyun",method = RequestMethod.GET)
//    public ResponseEntity<JsonResult> getCiYun(@RequestParam Integer trainId) {
//        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
//        String path = "";
//        try {
//            path = analysis.getwordCloud(trainId);
//            if (path != null) {
//                result.setStatus("ok");
//                result.setResult(path);
//            } else {
//                result.setStatus("fail");
//                result.setResult(path);
//            }
//        } catch (Exception e) {
//            result.setResult(e.getClass() + ":" + e.getMessage());
//            result.setStatus("error");
//        }
//        return ResponseEntity.ok(result);
//    }
}

