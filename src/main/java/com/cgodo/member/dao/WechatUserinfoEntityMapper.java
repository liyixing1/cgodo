package com.cgodo.member.dao;

import com.cgodo.member.entity.WechatUserinfoEntity;
import com.cgodo.member.entity.WechatUserinfoEntityCondition;
import com.cgodo.member.model.WechatUserinfoModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WechatUserinfoEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    int countByExample(WechatUserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    int deleteByExample(WechatUserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    int insert(WechatUserinfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    int insertSelective(WechatUserinfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    List<WechatUserinfoModel> selectByExampleWithRowbounds(WechatUserinfoEntityCondition example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    List<WechatUserinfoModel> selectByExample(WechatUserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    WechatUserinfoModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") WechatUserinfoEntity record, @Param("example") WechatUserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") WechatUserinfoEntity record, @Param("example") WechatUserinfoEntityCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(WechatUserinfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_userinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(WechatUserinfoEntity record);
}