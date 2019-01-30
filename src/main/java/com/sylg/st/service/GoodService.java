package com.sylg.st.service;

import com.sylg.st.dto.GoodName;
import com.sylg.st.dto.GoodsRecord;
import com.sylg.st.pojo.Goods;
import com.sylg.st.pojo.GoodsRecords;

import java.util.List;

public interface GoodService {
    /**
     * 添加新商品
     * */
    public int addNewGood(Goods goods);
    /**
     * 查询单个商品
     * */
    public Goods getGood(int goodId);
    /**
     * 删除产品
     * */
    public int deleteGood(int id);
    /**
     * 借出商品
     * */
    public int borrowGood(int memberId,int goodId,int num);
    /**
     * 归还商品
     * */
    public int giveBackGood(int memberId,int goodId, int num);
    /**
     * 查询某个人欠的商品名
     * */
    public List<GoodName> getMemberOwe(int memberId);
    public List<GoodsRecords> getNoGivrBackList();
    /**
     * 查看商品列表
     * */
    public List<Goods> getAllGoods();
    /**
     * 查看good的记录
     * */
    public List<GoodsRecords> getGoodRecords();
    public List<GoodsRecord> getDtoGoodRecords();
    /**
     * 删除商品使用记录
     * */
    public int deleteGoodRecord(int id);
    /**
     * 买good或者测试添加good的值进行使用
     * */
    public int buyGood(Integer goodId,int num,double money);
}
