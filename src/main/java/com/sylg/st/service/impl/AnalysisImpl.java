package com.sylg.st.service.impl;

import com.sylg.st.dto.QiandaoTongji;
import com.sylg.st.mapper.CommentMapper;
import com.sylg.st.mapper.CompetationMapper;
import com.sylg.st.mapper.QiandaobiaoMapper;
import com.sylg.st.mapper.TrainMapper;
import com.sylg.st.pojo.Comment;
import com.sylg.st.pojo.Qiandaobiao;
import com.sylg.st.pojo.Train;
import com.sylg.st.service.Analysis;
//import com.sylg.st.util.MakeWordCloud;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisImpl implements Analysis {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    TrainMapper trainMapper;
    @Autowired
    QiandaobiaoMapper qiandaobiaoMapper;

    /**
     *获取签到信息list，绘制折线图
     */
    @Override
    public List<QiandaoTongji> getQiandaoMessages() {
        List<QiandaoTongji> qiandaoTongjis = new ArrayList<>();
        List<Integer> trainIds = new ArrayList<>();
        try {
            List<Qiandaobiao> qiandaobiaos = qiandaobiaoMapper.selectAll();
            for (Qiandaobiao q: qiandaobiaos
                 ) {
                int trainId = q.getTrainId();
                if(trainIds.contains(trainId));
                else trainIds.add(trainId);
            }
            for (Integer trainId: trainIds
                 ) {
                QiandaoTongji qiandaoTongji = getQiandaoMessage(trainId);
                qiandaoTongjis.add(qiandaoTongji);
            }
            return qiandaoTongjis;
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 获取某次培训的签到信息，绘制饼状图
     * */
    @Override
    public QiandaoTongji getQiandaoMessage(int trainId) {
        int attendCount = 0;
        int unattendCount = 0;
        QiandaoTongji qiandaoTongji = new QiandaoTongji();
        try{
            List<Qiandaobiao> qiandaobiaos = qiandaobiaoMapper.selectByTrainId(trainId);
            for (Qiandaobiao q :qiandaobiaos
                 ) {
                if (q.getAttend()==1) attendCount++;
                else unattendCount++;
            }
            qiandaoTongji.setTrainId(trainId);
            qiandaoTongji.setAttend(attendCount);
            qiandaoTongji.setNoattend(unattendCount);
            return qiandaoTongji;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int addComment(String comment) {
        int order=0;
        try {
            int trainId = trainMapper.getLatestTrainId();
            Comment comment1 = (Comment) SpringUtil.getBean("Comment");
            comment1.setTrainId(trainId);
            comment1.setComment(comment);
            order = commentMapper.insert(comment1);
            return order;
        }catch (Exception e){
            return order;
        }
    }

//    @Override
//    public String getwordCloud(int trainId) {
//        String path ="st/wordcloud/"+trainId+".html";
//            String allComment = "";
//            List<Comment> comments = commentMapper.selectByComId(trainId);
//            for (Comment c: comments
//                 ) {
//                allComment+=c.getComment();
//            }
//            MakeWordCloud.makeWordCloud(allComment,trainId);
//        return path;
//    }

    @Override
    public double brightPhonePropotion() {
        return 0;
    }
}
