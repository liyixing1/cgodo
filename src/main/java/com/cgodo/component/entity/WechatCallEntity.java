package com.cgodo.component.entity;

import com.cgodo.constant.EnumStatus;
import com.cgodo.constant.EnumWechatInterfaceType;
import java.io.Serializable;
import java.util.Date;

public class WechatCallEntity implements Serializable {
    private String id;

    /**
     * 状态
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private EnumStatus status;

    /**
     * 版本号
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private Long version;

    /**
     * 创建时间
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private Date gmtCreated;

    /**
     * 修改时间
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private Date gmtUpdated;

    /**
     * OPEN_ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String openId;

    /**
     * IP
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String ip;

    /**
     * 调用者用户ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String userId;

    /**
     * 调用参数
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String params;

    /**
     * 调用结果
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String result;

    /**
     * 接口类型
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private EnumWechatInterfaceType type;

    /**
     * 外部编号，调用微信接口时关联起来
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String externalNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table wechat_call
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 状态
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public EnumStatus getStatus() {
        return status;
    }

    /**
     * 状态
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    /**
     * 版本号
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 创建时间
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 创建时间
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 修改时间
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public Date getGmtUpdated() {
        return gmtUpdated;
    }

    /**
     * 修改时间
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setGmtUpdated(Date gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }

    /**
     * OPEN_ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * OPEN_ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * IP
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getIp() {
        return ip;
    }

    /**
     * IP
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 调用者用户ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 调用者用户ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 调用参数
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getParams() {
        return params;
    }

    /**
     * 调用参数
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * 调用结果
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getResult() {
        return result;
    }

    /**
     * 调用结果
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    /**
     * 接口类型
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public EnumWechatInterfaceType getType() {
        return type;
    }

    /**
     * 接口类型
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setType(EnumWechatInterfaceType type) {
        this.type = type;
    }

    /**
     * 外部编号，调用微信接口时关联起来
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getExternalNo() {
        return externalNo;
    }

    /**
     * 外部编号，调用微信接口时关联起来
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo == null ? null : externalNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_call
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WechatCallEntity other = (WechatCallEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getGmtCreated() == null ? other.getGmtCreated() == null : this.getGmtCreated().equals(other.getGmtCreated()))
            && (this.getGmtUpdated() == null ? other.getGmtUpdated() == null : this.getGmtUpdated().equals(other.getGmtUpdated()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getExternalNo() == null ? other.getExternalNo() == null : this.getExternalNo().equals(other.getExternalNo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wechat_call
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getGmtCreated() == null) ? 0 : getGmtCreated().hashCode());
        result = prime * result + ((getGmtUpdated() == null) ? 0 : getGmtUpdated().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getExternalNo() == null) ? 0 : getExternalNo().hashCode());
        return result;
    }
}