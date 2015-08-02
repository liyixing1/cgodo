package cn.gou23.cgodo.server.dao;

import cn.gou23.cgodo.server.entity.ClientRequestEntity;
import cn.gou23.cgodo.server.entity.ClientRequestEntityCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClientRequestEntityMapper {
    int countByExample(ClientRequestEntityCondition example);

    int deleteByExample(ClientRequestEntityCondition example);

    int deleteByPrimaryKey(String id);

    int insert(ClientRequestEntity record);

    int insertSelective(ClientRequestEntity record);

    List<ClientRequestEntity> selectByExampleWithRowbounds(ClientRequestEntityCondition example, RowBounds rowBounds);

    List<ClientRequestEntity> selectByExample(ClientRequestEntityCondition example);

    ClientRequestEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClientRequestEntity record, @Param("example") ClientRequestEntityCondition example);

    int updateByExample(@Param("record") ClientRequestEntity record, @Param("example") ClientRequestEntityCondition example);

    int updateByPrimaryKeySelective(ClientRequestEntity record);

    int updateByPrimaryKey(ClientRequestEntity record);
}