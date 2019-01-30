package com.sylg.st.service;

import com.sylg.st.dto.ComList;
import com.sylg.st.pojo.Member;
import com.sylg.st.pojo.Membermac;
import com.sylg.st.pojo.Playphone;

import java.util.List;

public interface MacService {
    public int  addMac(int memberId,String mac);
    public int updateMac(int memberID,String mac);
    public int qiandao(List<String> strings);
    public List<ComList> getStudentXianshiList();
    public List<Member> getAllLeaders();
    public int playPhone(int playPhone,int all);
    public Playphone getPlayPHone(int trainId);
    public Membermac getMembermac(int memberId);
}
