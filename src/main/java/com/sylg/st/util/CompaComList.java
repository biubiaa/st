package com.sylg.st.util;

import com.sylg.st.dto.ComList;

import java.util.Comparator;

public class CompaComList implements Comparator<ComList> {
    @Override
    public int compare(ComList list1, ComList list2) {
        java.sql.Date date1 = java.sql.Date.valueOf(list1.getFabuTime());
//        System.out.println(date1.toString());
        java.sql.Date date2 = java.sql.Date.valueOf(list2.getFabuTime());
//        System.out.println(date2);
        return date1.compareTo(date2);
    }
}
