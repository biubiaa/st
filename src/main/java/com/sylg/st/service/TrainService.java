package com.sylg.st.service;

import com.sylg.st.dto.DtoQianDao;
import com.sylg.st.dto.MemberTrain;
import com.sylg.st.dto.TrainList;
import com.sylg.st.pojo.Pingfen;
import com.sylg.st.pojo.Train;

import java.util.List;

public interface TrainService {
    public int addTrain(Train train);
    public int deleteTrain(int id);
    public List<TrainList> getTrains();
    public Train getTrain(int trianId);
    public List<DtoQianDao> getQiandaoByTrainId(Integer trainId);
    public List<Pingfen> startPingfen(int th);
    public List<Pingfen> Pingfen(int th);
    public List<Train> getAllTrains();
    public List<MemberTrain> getMemberTrains(int memberId);
}
