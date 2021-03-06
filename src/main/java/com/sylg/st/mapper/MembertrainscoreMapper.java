package com.sylg.st.mapper;

import com.sylg.st.pojo.Membertrainscore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MembertrainscoreMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table membertrainscore
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table membertrainscore
     *
     * @mbggenerated
     */
    int insert(Membertrainscore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table membertrainscore
     *
     * @mbggenerated
     */
    Membertrainscore selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table membertrainscore
     *
     * @mbggenerated
     */
    List<Membertrainscore> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table membertrainscore
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Membertrainscore record);
    Membertrainscore selectBycomMemberId(@Param(value = "memberId") Integer memberId, @Param(value = "trainId") Integer trainId);
}