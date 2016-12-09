package com.cgodo.counter.dao;

import com.cgodo.counter.entity.CounterEntity;
import com.cgodo.counter.entity.CounterEntityCondition;
import com.cgodo.counter.model.CounterModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CounterEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int countByExample(CounterEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int deleteByExample(CounterEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int insert(CounterEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int insertSelective(CounterEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    List<CounterModel> selectByExampleWithRowbounds(CounterEntityCondition example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    List<CounterModel> selectByExample(CounterEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    CounterModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CounterEntity record, @Param("example") CounterEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CounterEntity record, @Param("example") CounterEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CounterEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CounterEntity record);
}