package com.sylg.st.service;

import com.sylg.st.dto.MoneyRecord;
import com.sylg.st.pojo.Moneyrecords;
import com.sylg.st.pojo.Moneyrecords;

import java.util.List;

public interface MoneyService {
    /**
     *添加一个账单记录
     * */
    public int addBill(double cost,String reason,String type);
    /**
     * 删除一个bill
     * */
    public int deleteBill(int id);
    /**
     * 修改一个bill
     * */
    public int updateBill(Moneyrecords moneyRecords);
    /**
     * 查询bill清单
     * */
    public List<MoneyRecord> getBills();
    /**
     * 获取剩余金额
     * */
    public double getMoney();
}
