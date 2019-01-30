package com.sylg.st.service;

import com.sylg.st.dto.QiandaoTongji;
import com.sylg.st.pojo.Qiandaobiao;

import java.util.List;

public interface Analysis {
    public int addComment(String comment);
//    public String getwordCloud(int trainId);
    public double brightPhonePropotion();
    public List<QiandaoTongji> getQiandaoMessages();
    public QiandaoTongji getQiandaoMessage(int trainId);
}
