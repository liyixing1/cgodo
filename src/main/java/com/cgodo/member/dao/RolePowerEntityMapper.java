package com.cgodo.member.dao;

import com.cgodo.member.entity.RolePowerEntity;
import com.cgodo.member.entity.RolePowerEntityCondition;
import com.cgodo.member.model.RolePowerModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RolePowerEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    int countByExample(RolePowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    int deleteByExample(RolePowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    int insert(RolePowerEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    int insertSelective(RolePowerEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    List<RolePowerModel> selectByExampleWithRowbounds(RolePowerEntityCondition example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    List<RolePowerModel> selectByExample(RolePowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    RolePowerModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RolePowerEntity record, @Param("example") RolePowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RolePowerEntity record, @Param("example") RolePowerEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RolePowerEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RolePowerEntity record);
}