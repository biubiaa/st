package com.sylg.st.service.impl;

import com.sylg.st.dto.GoodName;
import com.sylg.st.dto.GoodsRecord;
import com.sylg.st.mapper.*;
import com.sylg.st.pojo.*;
import com.sylg.st.service.GoodService;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    MoneyrecordsMapper moneyrecordsMapper;
    @Autowired
    GoodsrecordsMapper goodsrecordsMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    NogivebackMapper nogivebackMapper;
    @Override
    public int addNewGood(Goods good) {
        return goodsMapper.insert(good);
    }

    @Override
    public int buyGood(Integer goodId, int num,double money) {
        Goods  good = goodsMapper.selectByPrimaryKey(goodId);
        if(money!=0) {
            Moneyrecords moneyRecords = (Moneyrecords) SpringUtil.getBean("MoneyRecords");
            GoodsRecords goodsRecords = (GoodsRecords) SpringUtil.getBean("GoodsRecords");
            good.setGoodsCount(good.getGoodsCount() + num);
            goodsMapper.updateByPrimaryKey(good);
            goodsRecords.setState("买入");
            goodsRecords.setGoodsNumber(num);
            goodsRecords.setGoodsId(goodId);
            goodsrecordsMapper.insert(goodsRecords);
            moneyRecords.setMoneyCost(money);
            moneyRecords.setType("支出");
            moneyRecords.setMoneyReason("购买了" + good.getGoodsName() + num + "个");
            moneyRecords.setMoneyLeft(moneyrecordsMapper.getMoney() - money);
            moneyrecordsMapper.insert(moneyRecords);
        }else{
            good = goodsMapper.selectByPrimaryKey(goodId);
            good.setGoodsCount(num);
            goodsMapper.updateByPrimaryKey(good);
        }
        return 1;
    }

    @Override
    public Goods getGood(int goodId) {
        return goodsMapper.selectByPrimaryKey(goodId);
    }

    @Override
    public int deleteGood(int id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<GoodName> getMemberOwe(int memberId) {
        List<GoodName> goodNames = new ArrayList<>();
        try {
            List<Nogiveback> n = nogivebackMapper.selectAll();
            for (Nogiveback nn: n
                 ) {
                GoodsRecords record = goodsrecordsMapper.selectByPrimaryKey(nn.getGoodrecordId());
                if(record.getMemberId()==memberId){
                    GoodName goodName = new GoodName();
                    Goods g = goodsMapper.selectByPrimaryKey(record.getGoodsId());
                    goodName.setGoodId(record.getGoodsId());
                    goodName.setGoodName(g.getGoodsName());
                    goodNames.add(goodName);
                }
            }
        }catch (Exception  e){
            return null;
        }
        return goodNames;
    }

    @Override
    public int borrowGood(int memberId, int goodId, int num) {
        try {
            Goods good = goodsMapper.selectByPrimaryKey(goodId);
            GoodsRecords goodsRecord = new GoodsRecords();
            Nogiveback nogiveback = new Nogiveback();
            if (num <= good.getGoodsCount()) {
                good.setGoodsCount(good.getGoodsCount() - num);
                goodsMapper.updateByPrimaryKey(good);
                goodsRecord.setGoodsId(goodId);
                goodsRecord.setGoodsNumber(num);
                goodsRecord.setMemberId(memberId);
                if (good.getIsXiaohao() == 0)
                    goodsRecord.setState("借出");
                else
                    goodsRecord.setState("使用");
                goodsrecordsMapper.insert(goodsRecord);
                List<GoodsRecords> records = goodsrecordsMapper.selectAll();
                int id = records.get(records.size()-1).getGoodsrecordsId();
                nogiveback.setGoodrecordId(id);
                nogivebackMapper.insert(nogiveback);
                return 1;
            }
        }catch (Exception e){
            return -2;
        }
        return 0;
    }

    @Override
    public int giveBackGood(int memberId,int goodId, int num) {
//        GoodsRecords goodsRecords = new GoodsRecords();
        try {
            List<Nogiveback> nos = nogivebackMapper.selectAll();
            List<GoodsRecords> records  = new ArrayList<>();
            for (Nogiveback no:nos
                  ) {
                GoodsRecords re = goodsrecordsMapper.selectByPrimaryKey(no.getGoodrecordId());
                records.add(re);
            }
            int borrowCount = 0;
            for (GoodsRecords r: records
                 ) {
                if(r.getMemberId()==memberId&&r.getGoodsId()==goodId){
                    borrowCount+=r.getGoodsNumber();
                }
            }
            if(borrowCount==num){
                for (GoodsRecords re: records
                     ) {
                    if(re.getGoodsId()==goodId&&re.getMemberId()==memberId){
                        nogivebackMapper.deleteByGRIdKey(re.getGoodsrecordsId());
                    }

                }
                GoodsRecords r = new GoodsRecords();
                r.setState("已还");
                r.setGoodsNumber(num);
                r.setMemberId(memberId);
                r.setGoodsId(goodId);
                goodsrecordsMapper.insert(r);
                return 1;
            }else {
                return -2;
            }
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<GoodsRecords> getNoGivrBackList() {
        try{
            List<Nogiveback> nogivebacks = nogivebackMapper.selectAll();
            List<GoodsRecords> records = new ArrayList<>();
            for (Nogiveback no: nogivebacks
                 ) {
                GoodsRecords record = goodsrecordsMapper.selectByPrimaryKey(no.getGoodrecordId());
                records.add(record);
            }
            return records;
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public List<Goods> getAllGoods() {
        return goodsMapper.selectAll();
    }

    /**
     * 获取dto中good的集合
     * */
    @Override
    public List<GoodsRecord> getDtoGoodRecords() {
        List<GoodsRecords> goodsRecords = goodsrecordsMapper.selectAll();
        List<GoodsRecord> goodsRecords2 = new ArrayList<GoodsRecord>();
        for (GoodsRecords  g:
             goodsRecords) {
            if (g.getMemberId() != null) {
                Goods good = goodsMapper.selectByPrimaryKey(g.getGoodsId());
                Member member = memberMapper.selectByMemberId(g.getMemberId());
                GoodsRecord goodsRecord = new GoodsRecord();
                goodsRecord.setDate(g.getDate());
                goodsRecord.setGoodName(good.getGoodsName());
                goodsRecord.setMemberName(member.getMemberName());
                goodsRecord.setNum(g.getGoodsNumber());
                goodsRecord.setState(g.getState());
                goodsRecord.setGoodId(g.getGoodsId());
                goodsRecord.setMemberId(g.getMemberId());
                goodsRecord.setGoodRecordId(g.getGoodsrecordsId());
                goodsRecords2.add(goodsRecord);
            } else {
                Goods good = new Goods();
                GoodsRecord goodsRecord = new GoodsRecord();
                good = goodsMapper.selectByPrimaryKey(g.getGoodsId());
                goodsRecord.setDate(g.getDate());
                goodsRecord.setGoodName(good.getGoodsName());
                goodsRecord.setNum(g.getGoodsNumber());
                goodsRecord.setState(g.getState());
                goodsRecord.setGoodId(g.getGoodsId());
                goodsRecord.setGoodRecordId(g.getGoodsrecordsId());
                goodsRecords2.add(goodsRecord);
            }
        }

        return goodsRecords2;
    }

    @Override
    public List<GoodsRecords> getGoodRecords() {
        return goodsrecordsMapper.selectAll();
    }

    @Override
    public int deleteGoodRecord(int id) {
        return goodsrecordsMapper.deleteByPrimaryKey(id);
    }
}
