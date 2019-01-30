package com.sylg.st.web;

import com.sylg.st.dto.*;
import com.sylg.st.mapper.MemberMapper;
import com.sylg.st.mapper.QiandaobiaoMapper;
import com.sylg.st.pojo.Pingfen;
import com.sylg.st.pojo.Qiandaobiao;
import com.sylg.st.pojo.Train;
import com.sylg.st.service.impl.TrainServiceImpl;
import com.sylg.st.util.CompaMemberTrain;
import com.sylg.st.util.GetTrainTongji;
import com.sylg.st.util.JsonResult;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TrainController {
    @Autowired
    TrainServiceImpl trainService;
    @Autowired
    QiandaobiaoMapper qiandaobiaoMapper;
    @Autowired
    MemberMapper memberMapper;
    @RequestMapping(value = "addtrain",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addTrain(@RequestParam String trainName,@RequestParam String trainTime,
                                               @RequestParam String trainRoom){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Date train_time = new Date(trainTime);
        String str_trainTime = df1.format(train_time);
        String time = df.format(now).toString();
        try {
            Train train = (Train) SpringUtil.getBean("Train");
            train.setTrainName(trainName);
            train.setTrainExplain("");
            train.setTrainRoom(trainRoom);
            train.setTrainTime(str_trainTime);
            train.setFabuTime(time);
            int order = trainService.addTrain(train);
            if(order>0){
                result.setResult(order);
                result.setStatus("ok");
            }
            else{
                result.setStatus("fail");
                result.setResult(order);
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    @RequestMapping(value = "deletetrain",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> deleteTrain(@RequestParam int trainId){
        JsonResult result  = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            int order = trainService.deleteTrain(trainId);
            if(order>0){
                result.setResult(order);
                result.setStatus("ok");
            }
            else{
                result.setStatus("fail");
                result.setResult(order);
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "gettrainslist",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> deleteTrain(){
        JsonResult result  = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
                List<TrainList> trains = trainService.getTrains();
                result.setResult(trains);
                result.setStatus("ok");
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 获取签到表
     * */
    @RequestMapping(value = "qiandaolist",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> qiandaoList(@RequestParam Integer trainId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            List<DtoQianDao> dtoQianDaos = trainService.getQiandaoByTrainId(trainId);
            result.setResult(dtoQianDaos);
            result.setStatus("ok");
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 根据年级进行评分
     * */
    @RequestMapping(value = "pingfen",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> pingFen(@RequestParam Integer th){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<Pingfen> pingfen = trainService.Pingfen(th);
            if(pingfen.size()>0){
                result.setStatus("ok");
                result.setResult(pingfen);
            }else {
                result.setStatus("fail");
                result.setResult(pingfen);
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
@RequestMapping(value = "gettrain",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getTrain(@RequestParam int trainId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            Train train = trainService.getTrain(trainId);
            if(train!=null){
                result.setStatus("ok");
                result.setResult(train);
            }else {
                result.setStatus("fail");
                result.setResult("没有找到该培训的信息");
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
}
/**
 * 获取所有培训的年份
 * */
@RequestMapping(value = "gettrainyear",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getTrainYears(){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<Integer> years = new ArrayList<>();
            List<Train> trains = trainService.getAllTrains();
            SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int y=0;
            for(int i = 0;i<trains.size();i++){
                y=  df.parse(trains.get(i).getTrainTime()).getYear()+1900;
                if(years.contains(y));
                else years.add(y);
            }
            if(years.size()>0){
                result.setStatus("ok");
                result.setResult(years);
            }
            else {
                result.setResult(years);
                result.setStatus("fail");
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+"："+e.getMessage());
        }
        return ResponseEntity.ok(result);
}
/**
 * 根据年获取当年的培训
 * */
@RequestMapping(value = "gettrainsbyyear",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getTrainsByYear(@RequestParam Integer year){
    JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
    try{
        List<TrainName> needTrain = new ArrayList<>();
        List<Train> trains = trainService.getAllTrains();
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i= 0;i< trains.size();i++){
            int y = df.parse(trains.get(i).getTrainTime()).getYear()+1900;
            if(y==year){
                TrainName t = new TrainName();
                t.setTrainId(trains.get(i).getTrainId());
                t.setTrainName(trains.get(i).getTrainName());
                needTrain.add(t);
            }
        }
        if(needTrain.size()>0){
            result.setResult(needTrain);
            result.setStatus("ok");
        }else {
            result.setStatus("fail");
            result.setResult("未查到相关培训");
        }
    }catch (Exception e){
        result.setStatus("error");
        result.setResult(e.getClass()+":"+e.getMessage());
    }
    return ResponseEntity.ok(result);
}
/**
 * 获取签到统计表
 */
    @RequestMapping(value = "gettrainexcel",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getTrainExcel(@RequestParam Integer year){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<TrainName> needTrain = new ArrayList<>();
            List<Train> trains = trainService.getAllTrains();
            SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i= 0;i< trains.size();i++){
                int y = df.parse(trains.get(i).getTrainTime()).getYear()+1900;
                if(y==year){
                    TrainName t = new TrainName();
                    t.setTrainId(trains.get(i).getTrainId());
                    t.setTrainName(trains.get(i).getTrainName());
                    needTrain.add(t);
                }
            }
            List<Qiandaobiao> qiandaobiaos = new ArrayList<>();
            for (TrainName t: needTrain
                 ) {
                List<Qiandaobiao> q = qiandaobiaoMapper.selectByTrainId(t.getTrainId());
                qiandaobiaos.addAll(q);
            }
            List<MemberTrainReult> res = new ArrayList<>();
            MemberTrainReult reult1 = new MemberTrainReult();
            reult1.setScore(1);
            reult1.setMemberId(qiandaobiaos.get(0).getMemberId());
            reult1.setMemberName(memberMapper.selectByMemberId(qiandaobiaos.get(0).getMemberId()).getMemberName());
            res.add(reult1);
            qiandaobiaos.remove(0);
            for(int i = 0;i<qiandaobiaos.size();i++){
//                MemberTrainReult reult = new MemberTrainReult();
                for(int j =0;j<res.size();j++){
                    if (i==0){
                        MemberTrainReult reult2 = new MemberTrainReult();
                        reult2.setScore(1);
                        reult2.setMemberId(qiandaobiaos.get(i).getMemberId());
                        reult2.setMemberName(memberMapper.selectByMemberId(qiandaobiaos.get(i).getMemberId()).getMemberName());
                        res.add(reult2);
                        break;
                    }
                    if(qiandaobiaos.get(i).getMemberId()==res.get(j).getMemberId()){
                        res.get(j).setScore(res.get(j).getScore()+1);
                        break;
                    }else {
                        if(j==res.size()-1){
                            MemberTrainReult reult2 = new MemberTrainReult();
                            reult2.setScore(1);
                            reult2.setMemberId(qiandaobiaos.get(i).getMemberId());
                            reult2.setMemberName(memberMapper.selectByMemberId(qiandaobiaos.get(i).getMemberId()).getMemberName());
                            res.add(reult2);
                        }
                    }
                }
            }
            res.sort(new CompaMemberTrain());
            String path = GetTrainTongji.getTrainTongjiExcel(year,res);
            result.setResult(path);
            result.setStatus("ok");
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 获取出席比例
     * */
    @RequestMapping(value = "getchuxi",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getChuxi(@RequestParam Integer trainId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<Qiandaobiao> qiandaobiaos = qiandaobiaoMapper.selectByTrainId(trainId);
            ChuxiResult result1 = new ChuxiResult();
            int chuxi = 0;
            int weichuxi = 0;
            for (Qiandaobiao q: qiandaobiaos
                 ) {
                if (q.getAttend()==1){
                    chuxi++;
                }else {
                    weichuxi++;
                }
            }
            result1.setChuxi(chuxi );
            result1.setWeichuxi(weichuxi);
            result.setStatus("ok");
            result.setResult(result1);
        }catch (Exception e){
            result.setStatus("fail");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
}
