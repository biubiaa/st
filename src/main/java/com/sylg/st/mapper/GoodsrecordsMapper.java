package com.sylg.st.mapper;

import com.sylg.st.pojo.GoodsRecords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GoodsrecordsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goodsrecords
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer goodsrecordsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goodsrecords
     *
     * @mbggenerated
     */
    int insert(GoodsRecords record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goodsrecords
     *
     * @mbggenerated
     */
    GoodsRecords selectByPrimaryKey(Integer goodsrecordsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goodsrecords
     *
     * @mbggenerated
     */
    List<GoodsRecords> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goodsrecords
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GoodsRecords record);
    List<GoodsRecords> getgivebackId(Integer memberId,Integer goodId);
}