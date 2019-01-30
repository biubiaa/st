package com.sylg.st.service.impl;

import com.sylg.st.dto.ComList;
import com.sylg.st.mapper.*;
import com.sylg.st.pojo.*;
import com.sylg.st.service.MacService;
import com.sylg.st.util.CompaComList;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MacServiceImpl implements MacService {
    @Autowired
    MembermacMapper membermacMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    TrainMapper trainMapper;
    @Autowired
    CompetationMapper competationMapper;
    @Autowired
    TongzhiMapper tongzhiMapper;
    @Autowired
    PlayphoneMapper playphoneMapper;
    @Override
    public List<Member> getAllLeaders() {
        List<Member> allLeaders  = new ArrayList<>();
        try {
            List<Member> allMember = memberMapper.selectAll();
            for (Member member : allMember) {
                if (member.getMemberIdentify().equals("社长"))
                    allLeaders.add(member);
            }
            for (Member member : allMember) {
                if (member.getMemberIdentify().equals("副社长"))
                    allLeaders.add(member);
            }
            for (Member member : allLeaders) {
                if (member.getMemberIdentify().equals("部长"))
                    allLeaders.add(member);
            }
        }catch (Exception e){
            return null;
        }
        return allLeaders;
    }

    @Autowired
    QiandaobiaoMapper qiandaobiaoMapper;
    @Override
    public int addMac(int memberId, String mac) {
        Membermac membermac = (Membermac) SpringUtil.getBean("Membermac");
        membermac.setMac(mac);
        membermac.setMemberId(memberId);
        Member member = memberMapper.selectByPrimaryKey(memberId);
        membermac.setTh(member.getMemberTh());
        return membermacMapper.insert(membermac);
    }

    @Override
    public List<ComList> getStudentXianshiList() {
        List<ComList> tongzhiList = new ArrayList<ComList>();
        try {
            List<Competation> competations =competationMapper.selectAll();
            List<Train> trains = trainMapper.selectAll();
            List<Tongzhi> tongzhis = tongzhiMapper.selectAll();
            Collections.reverse(competations);
            Collections.reverse(trains);
            Collections.reverse(tongzhis);
            for (Competation com : competations
                 ) {
                ComList comList = new ComList();
                comList.setName(com.getCompetationName());
                comList.setFabuTime(com.getFabuTime());
                comList.setType("比赛");
//                comList.setId(com.getCompetationId());
                tongzhiList.add(comList);

            }
            for (Train train: trains
                 ) {
                ComList comList = new ComList();
                comList.setType("培训");
                comList.setFabuTime(train.getFabuTime());
                comList.setName(train.getTrainName());
//                comList.setId(train.getTrainId());
                tongzhiList.add(comList);
            }
            for (Tongzhi tongzhi: tongzhis) {
                ComList comList = new ComList();
                comList.setName(tongzhi.getTongzhiName());
                comList.setFabuTime(tongzhi.getTongzhiFabu());
                comList.setType("通知");
//                comList.setId(tongzhi.getId());
                tongzhiList.add(comList);
            }
            Collections.sort(tongzhiList,Collections.reverseOrder(new CompaComList()));
            for(int i = 0;i<tongzhiList.size();i++){
                tongzhiList.get(i).setId(i+1);
            }
        }catch (Exception e){
            return null;

        }
        return tongzhiList;
    }

    @Override
    public int playPhone(int playPhone, int all) {
        try{
            List<Train> trains = trainMapper.selectAll();
            int trainId = trains.get(trains.size()-1).getTrainId();
            Playphone p = playphoneMapper.selectBytrainId(trainId);
            if (p != null) {

                Playphone playphone = new Playphone();
                playphone.setAllnum(all);
                playphone.setComId(trainId);
                playphone.setXimienum(playPhone);
                playphoneMapper.updateByPrimaryKey(playphone);
            }else {
                Playphone playphone = new Playphone();
                playphone.setAllnum(all);
                playphone.setComId(trainId);
                playphone.setXimienum(playPhone);
                playphoneMapper.insert(playphone);
            }
        }catch (Exception e){

        }
        return 0;
    }

    @Override
    public Playphone getPlayPHone(int trainId) {
        try {
            Playphone playphone = playphoneMapper.selectBytrainId(trainId);
            return playphone;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Membermac getMembermac(int memberId) {
        return membermacMapper.selectBymemberId(memberId);
    }

    @Override
    public int qiandao(List<String> strings) {
        List<Membermac> membermacs ;
        List<Train> trains ;
        List<Integer> attendMember = new ArrayList<>();
        int trainId ;
//        try{
            membermacs = membermacMapper.selectAll();
            trains = trainMapper.selectAll();
            trainId = trains.get(trains.size()-1).getTrainId();
            if (trainId!=0) {
                for (String str : strings
                        ) {
                    for (int i = 0; i < membermacs.size(); i++) {
                        if (membermacs.get(i).getMac().substring(0,2).equals(str.substring(0,2))) {
                            attendMember.add(membermacs.get(i).getMemberId());
                            membermacs.remove(i);
                        }
                    }
                }
                for (Integer memberid : attendMember
                        ) {
                    Qiandaobiao q = new Qiandaobiao();
                    q.setMemberId(memberid);
                    q.setTrainId(trainId);
                    q.setAttend(1);
                    qiandaobiaoMapper.insert(q);
                }
                for (Membermac membermac:membermacs
                     ) {
                    Qiandaobiao qiandaobiao = new Qiandaobiao();
                    qiandaobiao.setTrainId(trainId);
                    qiandaobiao.setMemberId(membermac.getMemberId());
                    qiandaobiao.setAttend(0);
                    qiandaobiaoMapper.insert(qiandaobiao);
                }
            }
        return 1;
    }

    @Override
    public int updateMac(int memberId, String mac) {
        Membermac membermac = (Membermac) SpringUtil.getBean("Membermac");
        membermac.setMemberId(memberId);
        membermac.setMac(mac);
        Member member = memberMapper.selectByPrimaryKey(memberId);
        membermac.setTh(member.getMemberTh());
        return membermacMapper.updateByPrimaryKey(membermac);
    }
}
