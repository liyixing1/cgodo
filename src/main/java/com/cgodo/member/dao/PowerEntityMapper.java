package com.cgodo.member.dao;

import com.cgodo.member.entity.PowerEntity;
import com.cgodo.member.entity.PowerEntityCondition;
import com.cgodo.member.model.PowerModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PowerEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    int countByExample(PowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    int deleteByExample(PowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    int insert(PowerEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    int insertSelective(PowerEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    List<PowerModel> selectByExampleWithRowbounds(PowerEntityCondition example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    List<PowerModel> selectByExample(PowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    PowerModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PowerEntity record, @Param("example") PowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PowerEntity record, @Param("example") PowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PowerEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PowerEntity record);
}