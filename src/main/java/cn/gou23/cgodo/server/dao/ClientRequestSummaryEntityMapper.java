package cn.gou23.cgodo.server.dao;

import cn.gou23.cgodo.server.entity.ClientRequestSummaryEntity;
import cn.gou23.cgodo.server.entity.ClientRequestSummaryEntityCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClientRequestSummaryEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    int countByExample(ClientRequestSummaryEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    int deleteByExample(ClientRequestSummaryEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    int insert(ClientRequestSummaryEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    int insertSelective(ClientRequestSummaryEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    List<ClientRequestSummaryEntity> selectByExampleWithRowbounds(ClientRequestSummaryEntityCondition example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    List<ClientRequestSummaryEntity> selectByExample(ClientRequestSummaryEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    ClientRequestSummaryEntity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ClientRequestSummaryEntity record, @Param("example") ClientRequestSummaryEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ClientRequestSummaryEntity record, @Param("example") ClientRequestSummaryEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ClientRequestSummaryEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ClientRequestSummaryEntity record);
}