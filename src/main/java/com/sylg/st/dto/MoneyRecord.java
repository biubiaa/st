package com.sylg.st.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MoneyRecord {

    private Integer moneyId;


    private Double moneyCost;


    private Double moneyLeft;


    private String moneyDate;


    private String moneyReason;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMoneyDate(String moneyDate) {
        this.moneyDate = moneyDate;
    }

    public Integer getMoneyId() {
        return moneyId;
    }


    public void setMoneyId(Integer moneyId) {
        this.moneyId = moneyId;
    }


    public Double getMoneyCost() {
        return moneyCost;
    }

    public void setMoneyCost(Double moneyCost) {
        this.moneyCost = moneyCost;
    }


    public Double getMoneyLeft() {
        return moneyLeft;
    }


    public void setMoneyLeft(Double moneyLeft) {
        this.moneyLeft = moneyLeft;
    }


    public String getMoneyDate() {

        return this.moneyDate;
    }


    public void setMoneyDate(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.moneyDate = dateFormat.format(date);
    }

    public String getMoneyReason() {
        return moneyReason;
    }


    public void setMoneyReason(String moneyReason) {
        this.moneyReason = moneyReason == null ? null : moneyReason.trim();
    }
}
