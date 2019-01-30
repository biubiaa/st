package com.sylg.st.service;

import com.sylg.st.dto.ComList;
import com.sylg.st.dto.ComMember;
import com.sylg.st.dto.SomeoneCom;
import com.sylg.st.dto.TeamMessage;
import com.sylg.st.pojo.ComApplication;
import com.sylg.st.pojo.Competation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompetationService {
    //添加比赛附件
    public int addFujian(MultipartFile file);
    //添加比赛
    public int addCompetation(String comName,String comTime,String explain,
                              int memberLimit,String startbaoming,String jiezhibaoming,String generalize);
    //删除比赛
    public int deleteCompetation(int id);
    //获得所有比赛列表
    public List<ComList> getAllCompetations();
    //获取单个比赛的详细信息
    public Competation getCompetation(int comId);
    //检测某个比赛的网上报名选项
    public int checkApply(Competation competation);
    //获取单个成员报名过的所有比赛
    public List<SomeoneCom> getMemberAllCom(int memberId);
    //删除某队的报名信息
    public int giveUpApply(int memberId,int comId);
    //获取某个比赛中某个成员的团队信息
    public TeamMessage geTeamMembers(int memberId, int comId);
    //获取某个比赛的所有参赛人员信息
    public List<ComMember> getAllComMembers(int comId);

    //报名
    public int baoming(List<ComApplication> ap);
}
