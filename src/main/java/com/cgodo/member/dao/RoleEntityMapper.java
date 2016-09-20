package com.cgodo.member.dao;

import com.cgodo.member.entity.RoleEntity;
import com.cgodo.member.entity.RoleEntityCondition;
import com.cgodo.member.model.RoleModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int countByExample(RoleEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int deleteByExample(RoleEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int insert(RoleEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int insertSelective(RoleEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    List<RoleModel> selectByExampleWithRowbounds(RoleEntityCondition example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    List<RoleModel> selectByExample(RoleEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    RoleModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RoleEntity record, @Param("example") RoleEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RoleEntity record, @Param("example") RoleEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RoleEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RoleEntity record);
}