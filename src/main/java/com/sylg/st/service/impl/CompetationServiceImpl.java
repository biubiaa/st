package com.sylg.st.service.impl;

import com.sylg.st.dto.*;
import com.sylg.st.mapper.ComApplicationMapper;
import com.sylg.st.mapper.CompetationMapper;
import com.sylg.st.pojo.ComApplication;
import com.sylg.st.pojo.Competation;
import com.sylg.st.service.CompetationService;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CompetationServiceImpl  implements CompetationService {
    @Autowired
    CompetationMapper competationMapper;
    @Autowired
    ComApplicationMapper comApplicationMapper;

    @Override
    public int addFujian(MultipartFile file) {
        String filepath = "";
        try {
            if ( file!=null){
                byte[] fujian = file.getBytes();
                File file1 = new File("src\\main\\resources\\static\\fujian\\" + file.getOriginalFilename());
                FileOutputStream outputStream = new FileOutputStream(file1);
                outputStream.write(fujian);
                filepath = "st\\static\\fujian\\" + file.getOriginalFilename();
            }
            List<Competation> competations = competationMapper.selectAll();
            Competation competation = competations.get(competations.size()-1);
            if(competation.getCompetationFujian1()==null){
                competation.setCompetationFujian1(filepath);
                competationMapper.updateByPrimaryKey(competation);
            }
            else {
                competation.setCompetationFujian2(filepath);
                competationMapper.updateByPrimaryKey(competation);
            }
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    /**
     * 添加新的竞赛
     * */
    public int  addCompetation(String comName,String comTime,String explain,
                              int memberLimit,String startbaoming,String jiezhibaoming,String generalize) {
        Competation competation = (Competation) SpringUtil.getBean("Competation");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Date startTime = new Date(comTime);
        String str_StartTime = df1.format(startTime);
        String fabuTime = df.format(now);
        competation.setCompetationName(comName);
        competation.setCompetationTime(str_StartTime);
        competation.setMemberLimit(memberLimit);
        competation.setCompetationExplain(explain);
        competation.setGeneralization(generalize);
        competation.setStartbaoming(df.format( new Date(startbaoming)));
        if(jiezhibaoming.equals("")){
            competation.setStartapply(0);
            competation.setStartbaoming("");
        }else {
            competation.setStartapply(1);
            competation.setJiezhibaoming(df.format(new Date(jiezhibaoming)));
        }
        if(jiezhibaoming.equals("")){
            competation.setStartapply(0);
            competation.setJiezhibaoming("");
        }else {
            competation.setStartapply(1);
            competation.setJiezhibaoming(df.format(new Date(jiezhibaoming)));
        }

        competation.setFabuTime(fabuTime);
        return competationMapper.insert(competation);
    }
    /**
     * 根据竞赛id删除竞赛
     * */
    @Override
    public int deleteCompetation(int id) {
        return competationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Competation getCompetation(int comId) {
        Competation competation = competationMapper.selectByPrimaryKey(comId);
        competationMapper.updateByPrimaryKey(competation);
        return competation;
    }

    @Override
    public List<ComList> getAllCompetations() {
        List<Competation> competations= competationMapper.selectAll();
        java.sql.Date now ;
        for (Competation com: competations
             ) {
            checkApply(com);
        }
        competations = competationMapper.selectAll();
        List<ComList> comLists = new ArrayList<ComList>();
        for (Competation c: competations
             ) {
            ComList comList  = new ComList();
            comList.setFabuTime(c.getFabuTime());
            comList.setId(c.getCompetationId());
            comList.setName(c.getCompetationName());
            comList.setGeneralization(c.getGeneralization());
            comLists.add(comList);
        }
        return comLists;
    }
    /**
     * 检测网上报名
     * */
    @Override
    public int checkApply(Competation competation) {
        try {
            java.sql.Date date = java.sql.Date.valueOf(competation.getJiezhibaoming());
            java.sql.Date now  = new java.sql.Date(new Date().getTime());
            int i = now.compareTo(date);
            if(i>0){
                competation.setStartapply(0);
                competationMapper.updateByPrimaryKey(competation);
            }
            return i;
        }catch (Exception e) {
            return 0;
        }
    }

    /**
     * 放弃报名
     * */
    @Override
    public int giveUpApply(int memberId, int comId) {
        try {
            ComApplication comApplication = comApplicationMapper.selectByMemberId(memberId, comId);
            String biaoshi = comApplication.getTeamBiaoshi();
            List<ComApplication> comApplications = comApplicationMapper.selectByBiaoshi(biaoshi);
            for (ComApplication c : comApplications
                    ) {
                comApplicationMapper.deleteByPrimaryKey(c.getId());
            }
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
    /**
     * 获取某比赛某成员团队信息
     * */
    @Override
    public TeamMessage geTeamMembers(int memberId, int comId) {
        ComApplication comApplication = comApplicationMapper.selectByMemberId(memberId,comId);
        Competation competation  = competationMapper.selectByPrimaryKey(comId);
        String biaoshi = comApplication.getTeamBiaoshi();
        List<ComApplication> comApplications = comApplicationMapper.selectByBiaoshi(biaoshi);
        TeamMessage teamMessage = new TeamMessage();
        teamMessage.setCapacity(comApplications.size());
        teamMessage.setComId(comId);
        teamMessage.setComName(competation.getCompetationName());
        for(int i = 0;i<comApplications.size();i++){
            if(i==0){
                teamMessage.setMember1Id(comApplications.get(i).getId());
                teamMessage.setMember1Name(comApplications.get(i).getMemberName());
                teamMessage.setMember1phone(comApplications.get(i).getMemberPhone().toString());
                teamMessage.setMember1qq(comApplications.get(i).getMemberQq());
                teamMessage.setMember1School(comApplications.get(i).getMemberSchool());
            }
            if(i==1){
                teamMessage.setMember2Id(comApplications.get(i).getId());
                teamMessage.setMember2Name(comApplications.get(i).getMemberName());
                teamMessage.setMember2phone(comApplications.get(i).getMemberPhone().toString());
                teamMessage.setMember2qq(comApplications.get(i).getMemberQq());
                teamMessage.setMember2School(comApplications.get(i).getMemberSchool());
            }
            if(i==2){
                teamMessage.setMember3Id(comApplications.get(i).getId());
                teamMessage.setMember3Name(comApplications.get(i).getMemberName());
                teamMessage.setMember3phone(comApplications.get(i).getMemberPhone().toString());
                teamMessage.setMember3qq(comApplications.get(i).getMemberQq());
                teamMessage.setMember3School(comApplications.get(i).getMemberSchool());
            }
            if(i==3){
                teamMessage.setMember4Id(comApplications.get(i).getId());
                teamMessage.setMember4Name(comApplications.get(i).getMemberName());
                teamMessage.setMember4phone(comApplications.get(i).getMemberPhone().toString());
                teamMessage.setMember4qq(comApplications.get(i).getMemberQq());
                teamMessage.setMember4School(comApplications.get(i).getMemberSchool());
            }
            if(i==4){
                teamMessage.setMember5Id(comApplications.get(i).getId());
                teamMessage.setMember5Name(comApplications.get(i).getMemberName());
                teamMessage.setMember5phone(comApplications.get(i).getMemberPhone().toString());
                teamMessage.setMember5qq(comApplications.get(i).getMemberQq());
                teamMessage.setMember5School(comApplications.get(i).getMemberSchool());
            }
        }


        return teamMessage;
    }
/**
 * 获取某个成员报名过的所有比赛
 * */
    @Override
    public List<SomeoneCom> getMemberAllCom(int memberId) {
        List<ComApplication> comApplications =comApplicationMapper.selectAllByMemberId(memberId);
        List<SomeoneCom> someoneComs = new ArrayList<SomeoneCom>();
        for (ComApplication c:comApplications
             ) {
            Competation competation = competationMapper.selectByPrimaryKey(c.getComId());
            SomeoneCom someoneCom = new SomeoneCom();
            someoneCom.setCaptain(c.getCaptain());
            someoneCom.setComId(c.getComId());
            someoneCom.setComName(competation.getCompetationName());
            someoneCom.setTeamName(c.getTeamName());
            someoneCom.setMemberId(c.getMemberId());
            someoneCom.setMemberName(c.getMemberName());
            someoneComs.add(someoneCom);
            String unique = c.getTeamBiaoshi();
            List<ComApplication> cc = comApplicationMapper.selectByBiaoshi(unique);
            someoneCom.setNum(cc.size());
            for (int i = 0; i < cc.size(); i++) {
                if (cc.get(i).getMemberId() == memberId)
                    cc.remove(cc.get(i));
            }
            for (ComApplication c1 : cc
                    ) {
                Friend friend = new Friend();
                friend.setMemberId(c1.getMemberId());
                friend.setMemberName(c1.getMemberName());
                friend.setMailbox(c1.getMailbox());
                friend.setPhone(c1.getMemberPhone());
                someoneCom.getFriends().add(friend);
            }
        }


        return someoneComs;
    }
    /**
     * 报名
     * */
    @Override
    public int baoming(List<ComApplication> ap) {
        try {
            for (ComApplication ca: ap
                 ) {
                comApplicationMapper.insert(ca);
            }
            return 1;
        }catch (Exception e) {
            return 0;
        }
    }

    /**
 * 获取参加某比赛的所有成员
 * */
    @Override
    public List<ComMember> getAllComMembers(int comId) {
        List<ComMember> members = new ArrayList<ComMember>();
        List<ComApplication> comApplications = comApplicationMapper.selectByComId(comId);
        for (ComApplication com: comApplications
             ) {
            ComMember member = new ComMember();
            member.setId(com.getMemberId());
            member.setSchool(com.getMemberSchool());
            member.setName(com.getMemberName());
            member.setTeamName(com.getTeamName());
            member.setQq(com.getMailbox());
            member.setCaptain(com.getCaptain());
            member.setPhone(com.getMemberPhone().toString());
            members.add(member);
        }
        return members;
    }
}
