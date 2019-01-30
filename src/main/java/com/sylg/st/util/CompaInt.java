package com.sylg.st.util;

import java.util.Comparator;

public class CompaInt implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 > o2 ? 0 : 1;
    }
}
