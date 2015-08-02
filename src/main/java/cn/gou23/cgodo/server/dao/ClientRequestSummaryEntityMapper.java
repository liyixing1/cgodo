package cn.gou23.cgodo.server.dao;

import cn.gou23.cgodo.server.entity.ClientRequestSummaryEntity;
import cn.gou23.cgodo.server.entity.ClientRequestSummaryEntityCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClientRequestSummaryEntityMapper {
    int countByExample(ClientRequestSummaryEntityCondition example);

    int deleteByExample(ClientRequestSummaryEntityCondition example);

    int deleteByPrimaryKey(String id);

    int insert(ClientRequestSummaryEntity record);

    int insertSelective(ClientRequestSummaryEntity record);

    List<ClientRequestSummaryEntity> selectByExampleWithRowbounds(ClientRequestSummaryEntityCondition example, RowBounds rowBounds);

    List<ClientRequestSummaryEntity> selectByExample(ClientRequestSummaryEntityCondition example);

    ClientRequestSummaryEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClientRequestSummaryEntity record, @Param("example") ClientRequestSummaryEntityCondition example);

    int updateByExample(@Param("record") ClientRequestSummaryEntity record, @Param("example") ClientRequestSummaryEntityCondition example);

    int updateByPrimaryKeySelective(ClientRequestSummaryEntity record);

    int updateByPrimaryKey(ClientRequestSummaryEntity record);
}