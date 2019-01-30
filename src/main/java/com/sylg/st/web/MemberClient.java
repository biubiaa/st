package com.sylg.st.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sylg.st.dto.ComList;
import com.sylg.st.dto.MemberClientMessage;
import com.sylg.st.dto.MemberTrain;
import com.sylg.st.dto.TestDuiXiang;
import com.sylg.st.mapper.CompetationMapper;
import com.sylg.st.mapper.MemberMapper;
import com.sylg.st.mapper.MembermacMapper;
import com.sylg.st.mapper.TrainMapper;
import com.sylg.st.pojo.ComApplication;
import com.sylg.st.pojo.Competation;
import com.sylg.st.pojo.Member;
import com.sylg.st.pojo.Train;
import com.sylg.st.service.impl.CompetationServiceImpl;
import com.sylg.st.service.impl.TrainServiceImpl;
import com.sylg.st.util.CompaComList;
import com.sylg.st.util.JsonResult;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@RestController
public class MemberClient {
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    MembermacMapper membermacMapper;
    @Autowired
    CompetationMapper competationMapper;
    @Autowired
    TrainMapper trainMapper;
    @Autowired
    TrainServiceImpl trainService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    CompetationServiceImpl competationService;
    /**
     * 报名
     * */
    @RequestMapping(value = "baoming",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> baoMing(){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            BufferedReader reader = request.getReader();
            String str = reader.readLine();
            List<ComApplication> ap =new ArrayList<>();
            JSONObject j = JSONObject.parseObject(str);
            int comId = j.getInteger("comId");
            String teamName = j.getString("teamName");
            String teacher = j.getString("teacher");
            JSONArray j1 = (JSONArray) JSONArray.parse(j.getString("baoming"));
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String biaoshi = sf.format(new Date());
            for (int i = 0;i<j1.size();i++){
                JSONObject j2 = JSONObject.parseObject(j1.getString(i));
                ComApplication app = new ComApplication();
                app.setMemberId(j2.getInteger("memberId"));
                app.setMemberName(j2.getString("memberName"));
                app.setMemberQq("");
                app.setMemberSchool(j2.getString("memberSchool"));
                app.setTeamKouhao("");
                app.setComType("");
                app.setComId(comId);
                app.setTeamCapacity(j1.size());
                app.setMemberPhone(j2.getString("memberPhone"));
                app.setMailbox(j2.getString("memberBox"));
                app.setTeacher(teacher);
                app.setTeamName(teamName);
                app.setTeamBiaoshi(biaoshi);
                if (i==0){
                    app.setCaptain(1);
                }else {
                    app.setCaptain(0);
                }
                ap.add(app);
            }
            int order = competationService.baoming(ap);
            if(order>0){
                result.setStatus("ok");
                result.setResult(order);
            }else {
                result.setStatus("fail");
                result.setResult(order);
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    @RequestMapping(value = "getmembermessage",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getMemberMessage(@RequestParam Integer memberId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            Member member = memberMapper.selectByMemberId(memberId);
            String mac  = membermacMapper.selectByPrimaryKey(memberId).getMac();
            MemberClientMessage memberClientMessage = new MemberClientMessage();
            if (mac!=null) memberClientMessage.setMac(mac);
            else memberClientMessage.setMac("未绑定");
            memberClientMessage.setMemberId(memberId);
            memberClientMessage.setMemberName(member.getMemberName());
            memberClientMessage.setDepartment(member.getDepartmentName());
            memberClientMessage.setPhone(member.getMemberPhone());
            memberClientMessage.setSchool(member.getSchoolName());
            memberClientMessage.setQq(member.getMemberQq());
            memberClientMessage.setWechat(member.getMemberWechat());
            result.setStatus("ok");
            result.setResult(memberClientMessage);

        }catch (Exception e){

        }
        return ResponseEntity.ok(result);
    }

    /**
     * 获取通知清单
     * */
    @RequestMapping(value = "gettongzhi",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getTongzhi(){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<Competation> competations = competationMapper.selectAll();
            List<Train> trains = trainMapper.selectAll();
            List<ComList> comLists = new ArrayList<>();
            for (Train t: trains
                 ) {
                ComList comList = new ComList();
                comList.setId(t.getTrainId());
                comList.setGeneralization(t.getTrainExplain());
                comList.setFabuTime(t.getFabuTime());
                comList.setType("培训");
                comList.setName(t.getTrainName());
                comLists.add(comList);
            }
            for (Competation c: competations
                 ) {
                ComList comList = new ComList();
                comList.setName(c.getCompetationName());
                comList.setType("竞赛");
                comList.setFabuTime(c.getFabuTime());
                comList.setGeneralization(c.getGeneralization());
                comList.setId(c.getCompetationId());
                comLists.add(comList);
            }
            comLists.sort(new CompaComList());
            if(comLists.size()>0){
                result.setStatus("ok");
                result.setResult(comLists);
            }else {
                result.setStatus("fail");
                result.setResult(comLists);
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return  ResponseEntity.ok(result);
    }
    /**
     * 获取一个member的所有培训情况
     * */
    @RequestMapping(value = "getmembertrains",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getMemberTrains(@RequestParam Integer memberId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<MemberTrain> mts = trainService.getMemberTrains(memberId);
            if(mts.size()>0){
                result.setStatus("ok");
                result.setResult(mts);
            }else {
                result.setStatus("fail");
                result.setResult("你还没有参加过活动");
            }
        }catch (Exception e ){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     *获取通知详情
     * */
    @RequestMapping(value = "getjutitongzhi",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getTongzhi(@RequestParam Integer id,@RequestParam String type){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            if(type.equals("竞赛")){

                Competation competation = competationMapper.selectByPrimaryKey(id);
                String jiehzibaoming = competation.getJiezhibaoming();
                String startbaoming = competation.getStartbaoming();
                try {
                    String now = df.format(new Date());
                    if (df.parse(competation.getJiezhibaoming()).compareTo(df.parse(now)) == df.parse(now).compareTo(df.parse(competation.getStartbaoming()))) {
                        competation.setStartapply(1);

                    } else {
                        competation.setStartapply(0);
                    }
                }catch (Exception e){
                    competation.setStartapply(0);
                }
                competationMapper.updateByPrimaryKey(competation);
                result.setStatus("ok");
                result.setResult(competation);
            }
            if (type.equals("培训")){
                Train train = trainMapper.selectByPrimaryKey(id);
                result.setStatus("ok");
                result.setResult(train);
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 修改成员信息
     * */
    @RequestMapping(value = "updatemembermes",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> updateMemberMes(@RequestParam Integer memberId,@RequestParam String memberName,
                                                      @RequestParam String school,@RequestParam String department,
                                                      @RequestParam String qq,@RequestParam String phone,
                                                      @RequestParam String wechat){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{

            Member member = new Member();
            member.setMemberId(memberId);
            member.setMemberName(memberName);
            member.setSchoolName(school);
            member.setDepartmentName(department);
            member.setMemberQq(Integer.parseInt(qq));
            member.setMemberPhone(phone);
            member.setMemberWechat(wechat);
            member.setMemberSex(memberMapper.selectByMemberId(memberId).getMemberSex());
            int order = memberMapper.updateByPrimaryKey(member);
            if(order>0){
                result.setStatus("ok");
                result.setResult(order);
            }else {
                result.setStatus("fail");
                if(qq.length()>10){
                    result.setResult("QQ号长度或者格式错误，请核对");
                }
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 获取管理成员信息
     * */
    @RequestMapping(value = "getmenager",method = RequestMethod.GET)
    public  ResponseEntity<JsonResult> getMenager(@RequestParam Integer th){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<Member> members = memberMapper.getMembersByTh(th);
            List<Member> menagers = new ArrayList<>();
            for (Member m: members
                    ) {
                if(m.getMemberIdentify().equals("社长")){
                    menagers.add(m);
                    break;
                }
            }
            for (Member m: members
                    ) {
                if(m.getMemberIdentify().equals("副社长")){
                    menagers.add(m);
                }
            }
            for (Member m: members
                    ) {
                if(m.getMemberIdentify().equals("部长")){
                    menagers.add(m);
                }
            }
            for (Member m: members
                    ) {
                if(m.getMemberIdentify().equals("副部长")){
                    menagers.add(m);
                }
            }
            if(menagers.size()>0){
                result.setStatus("ok");
                result.setResult(menagers);
            }else {
                result.setStatus("fail");
                result.setResult(menagers);
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
}
