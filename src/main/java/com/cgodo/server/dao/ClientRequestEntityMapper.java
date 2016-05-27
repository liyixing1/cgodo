package com.cgodo.server.dao;

import com.cgodo.server.entity.ClientRequestEntity;
import com.cgodo.server.entity.ClientRequestEntityCondition;
import com.cgodo.server.model.ClientRequestModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClientRequestEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    int countByExample(ClientRequestEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    int deleteByExample(ClientRequestEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    int insert(ClientRequestEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    int insertSelective(ClientRequestEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    List<ClientRequestModel> selectByExampleWithRowbounds(ClientRequestEntityCondition example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    List<ClientRequestModel> selectByExample(ClientRequestEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    ClientRequestModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ClientRequestEntity record, @Param("example") ClientRequestEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ClientRequestEntity record, @Param("example") ClientRequestEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ClientRequestEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ClientRequestEntity record);
}