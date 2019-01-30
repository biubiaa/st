package com.sylg.st.util;



import com.sylg.st.pojo.Pingfen;

import java.util.Comparator;

public class CompaPingfen implements Comparator<Pingfen> {
    @Override
    public int compare(Pingfen o1, Pingfen o2) {
        return  o1.getScore()>o2.getScore()?1:0;
    }
}
