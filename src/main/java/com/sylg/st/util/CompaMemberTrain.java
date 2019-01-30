package com.sylg.st.util;

import com.sylg.st.dto.MemberTrainReult;
import com.sylg.st.pojo.Pingfen;

import java.util.Comparator;

public class CompaMemberTrain implements Comparator<MemberTrainReult> {
    @Override
    public int compare(MemberTrainReult o1, MemberTrainReult o2) {
        return o1.getScore()>o2.getScore()?1:0;
    }
}
