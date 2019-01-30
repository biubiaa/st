package com.sylg.st.mapper;

import com.sylg.st.pojo.Memberpassword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MemberpasswordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table memberpassword
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer memberId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table memberpassword
     *
     * @mbggenerated
     */
    int insert(Memberpassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table memberpassword
     *
     * @mbggenerated
     */
    Memberpassword selectByPrimaryKey(Integer memberId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table memberpassword
     *
     * @mbggenerated
     */
    List<Memberpassword> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table memberpassword
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Memberpassword record);
}