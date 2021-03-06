package com.sylg.st.mapper;

import com.sylg.st.pojo.Qiandaobiao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface QiandaobiaoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qiandaobiao
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qiandaobiao
     *
     * @mbggenerated
     */
    int insert(Qiandaobiao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qiandaobiao
     *
     * @mbggenerated
     */
    Qiandaobiao selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qiandaobiao
     *
     * @mbggenerated
     */
    List<Qiandaobiao> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qiandaobiao
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Qiandaobiao record);
    List<Qiandaobiao> selectByTrainId(Integer trainId);
    List<Qiandaobiao> selectByMemberId(Integer memberId);
}