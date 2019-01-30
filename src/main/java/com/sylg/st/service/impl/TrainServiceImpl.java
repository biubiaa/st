package com.sylg.st.service.impl;

import com.sylg.st.dto.DtoQianDao;
import com.sylg.st.dto.MemberTrain;
import com.sylg.st.dto.TrainList;
import com.sylg.st.mapper.*;
import com.sylg.st.pojo.*;
import com.sylg.st.service.TrainService;
import com.sylg.st.util.CompaPingfen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    TrainMapper trainMapper;
    @Autowired
    QiandaobiaoMapper qiandaobiaoMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    PingfenMapper pingfenMapper;
    @Autowired
    MembertrainscoreMapper membertrainscoreMapper;
    @Override
    public int addTrain(Train train) {
        return trainMapper.insert(train);
    }

    @Override
    public List<MemberTrain> getMemberTrains(int memberId) {
        List<Qiandaobiao> qiandaobiaos = qiandaobiaoMapper.selectByMemberId(memberId);
        List<MemberTrain> mts = new ArrayList<>();
        for (Qiandaobiao q: qiandaobiaos
             ) {
            MemberTrain mt = new MemberTrain();
            Train train = trainMapper.selectByPrimaryKey(q.getTrainId());
            mt.setTrainTime(train.getTrainTime());
            mt.setTrainName(train.getTrainName());
            mt.setAttend(q.getAttend()==1?"出席":"未出席");
            if(q.getAttend()==1) {
                Membertrainscore membertrainscore =  membertrainscoreMapper.selectBycomMemberId(memberId,train.getTrainId());
                if (membertrainscore==null) {
                    membertrainscore = new Membertrainscore();
                    membertrainscore.setMemberId(memberId);
                    membertrainscore.setTrainId(train.getTrainId());
                    double score = 20 + (double) (Math.random() * ((70 - 20) + 1));
                    membertrainscore.setScore(score);
                    membertrainscoreMapper.insert(membertrainscore);
                    mt.setScore(score);
                }else {
                    Membertrainscore membertrainscore1 = membertrainscoreMapper.selectBycomMemberId(memberId,train.getTrainId());
                    mt.setScore(membertrainscore1.getScore());
                }
            }else mt.setScore(0);
            mts.add(mt);
        }
        return mts;
    }

    @Override
    public Train getTrain(int trianId) {
        return trainMapper.selectByPrimaryKey(trianId);
    }

    @Override
    public List<Train> getAllTrains() {
        List<Train> trains = trainMapper.selectAll();
        return trains;
    }

    @Override
    public List<Pingfen> Pingfen(int th) {
        try{
            List<Pingfen> pingfens = pingfenMapper.selectByTh(th);
            int count = 1;
            for (Pingfen pf: pingfens
                 ) {
                pf.setId(count);
                count++;
            }
            if(pingfens.size()!=0){
                return pingfens;
            }else {
                pingfens = startPingfen(th);
                return pingfens;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override

    public List<Pingfen> startPingfen(int th) {
        List<Member> members  = memberMapper.getMembersByTh(th);
        List<Pingfen> results = new ArrayList<>();
        for (Member m: members
             ) {
            Pingfen result = new Pingfen();
            result.setMemberid(m.getMemberId());
            result.setMemberName(m.getMemberName());
            result.setTh(th);
            List<Qiandaobiao> qiandaobiaos = qiandaobiaoMapper.selectByMemberId(m.getMemberId());
            double score = 0;
            for (Qiandaobiao q: qiandaobiaos
                 ) {
                score+=(double)q.getAttend();
            }
            result.setScore(score);
            results.add(result);
        }
        results.sort(new CompaPingfen());
        int Min = 95;
        int Max = 99;
        double score = 0;
        for (int i = 0;i<results.size();i++){
            if(i<results.size()-1) {
                if (results.get(i).getScore() == results.get(i + 1).getScore()) {
                    score = Min + (double) (Math.random() * ((Max - Min) + 1));
                } else {
                    score = Min + (double) (Math.random() * ((Max - Min) + 1));
                    Min -= 5;
                    Max -= 5;
                }
            }else {
                score = Min + (double) (Math.random() * ((Max - Min) + 1));
            }
            results.get(i).setScore(score);
            results.sort(new CompaPingfen());
        }
        for (Pingfen p: results
             ) {
            pingfenMapper.insert(p);
        }
        return results;
    }

    @Override
    public List<DtoQianDao> getQiandaoByTrainId(Integer trainId) {
        List<Qiandaobiao> qiandaobiaos = qiandaobiaoMapper.selectByTrainId(trainId);
        List<DtoQianDao> dtoQianDaos = new ArrayList<>();
        for (Qiandaobiao q: qiandaobiaos
             ) {
            DtoQianDao dtoQianDao = new DtoQianDao();
            Member member = memberMapper.selectByMemberId(q.getMemberId());
            dtoQianDao.setMemberId(q.getMemberId());
            dtoQianDao.setMemnberName(member.getMemberName());
            dtoQianDao.setAttend(q.getAttend());
            dtoQianDaos.add(dtoQianDao);
        }
        return dtoQianDaos;
    }

    @Override
    public List<TrainList> getTrains() {
        List<Train> trains = trainMapper.selectAll();
        List<TrainList> trainLists = new ArrayList<TrainList>();
        for (Train train: trains
             ) {
            TrainList trainList = new TrainList();
            trainList.setTrainId(train.getTrainId());
            trainList.setFabuTime(train.getFabuTime());
            trainList.setTrainName(train.getTrainName());
            trainList.setLocation(train.getTrainRoom());
            trainLists.add(trainList);
        }
        return trainLists;
    }

    @Override
    public int deleteTrain(int id) {
        return trainMapper.deleteByPrimaryKey(id);
    }
}
