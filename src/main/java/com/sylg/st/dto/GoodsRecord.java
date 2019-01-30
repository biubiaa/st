package com.sylg.st.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoodsRecord {
    private Integer goodRecordId;
    private Integer goodId;
    private String goodName;
    private Integer memberId;

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    private String memberName;
    private Integer num;
    private String date;
    private String state;

    public Integer getGoodRecordId() {
        return goodRecordId;
    }

    public void setGoodRecordId(Integer goodRecordId) {
        this.goodRecordId = goodRecordId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.date = dateFormat.format(date);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
