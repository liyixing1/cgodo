package com.cgodo.member.dao;

import com.cgodo.member.entity.UserinfoEntity;
import com.cgodo.member.entity.UserinfoEntityCondition;
import com.cgodo.member.model.UserinfoModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserinfoEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    int countByExample(UserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    int deleteByExample(UserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    int insert(UserinfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    int insertSelective(UserinfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    List<UserinfoModel> selectByExampleWithRowbounds(UserinfoEntityCondition example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    List<UserinfoModel> selectByExample(UserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    UserinfoModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UserinfoEntity record, @Param("example") UserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UserinfoEntity record, @Param("example") UserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserinfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserinfoEntity record);
}