package com.sylg.st.service.impl;

import com.sylg.st.dto.MoneyRecord;
import com.sylg.st.mapper.MoneyrecordsMapper;
import com.sylg.st.pojo.Moneyrecords;
import com.sylg.st.service.MoneyService;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    MoneyrecordsMapper moneyrecordsMapper;
    @Override
    public int addBill(double cost,String reason,String type) {
        Moneyrecords moneyRecords = new Moneyrecords();
        if (type.equals("sr")) {
            moneyRecords.setMoneyCost(cost);
            moneyRecords.setMoneyReason(reason);
            moneyRecords.setMoneyLeft(getMoney() + cost);
            moneyRecords.setType("收入");
            return moneyrecordsMapper.insert(moneyRecords);
        }else {
            moneyRecords.setMoneyCost(cost);
            moneyRecords.setMoneyReason(reason);
            moneyRecords.setMoneyLeft(getMoney() - cost);
            moneyRecords.setType("支出");
            return moneyrecordsMapper.insert(moneyRecords);
        }
    }

    @Override
    public int deleteBill(int id) {
        return moneyrecordsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateBill(Moneyrecords moneyrecords) {
        return moneyrecordsMapper.updateByPrimaryKey(moneyrecords);
    }

    @Override
    public List<MoneyRecord> getBills() {
        List<Moneyrecords> moneyRecords = moneyrecordsMapper.selectAll();
        List<MoneyRecord> moneyRecords1 = new ArrayList<MoneyRecord>();
        for (Moneyrecords m: moneyRecords
             ) {
            MoneyRecord moneyRecord = new MoneyRecord();
            moneyRecord.setMoneyCost(m.getMoneyCost());
            moneyRecord.setMoneyDate(m.getMoneyDate());
            moneyRecord.setMoneyId(m.getMoneyId());
            moneyRecord.setMoneyLeft(m.getMoneyLeft());
            moneyRecord.setMoneyReason(m.getMoneyReason());
            moneyRecord.setState(m.getType());
            moneyRecords1.add(moneyRecord);
        }
        return moneyRecords1;
    }

    @Override
    public double getMoney() {
        return moneyrecordsMapper.getMoney();
    }
}
