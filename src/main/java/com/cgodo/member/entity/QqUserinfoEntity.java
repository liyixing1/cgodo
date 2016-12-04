package com.cgodo.member.entity;

import com.cgodo.constant.EnumStatus;
import java.io.Serializable;
import java.util.Date;

public class QqUserinfoEntity implements Serializable {
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
     * QQopenid
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String openId;

    /**
     * 用户ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table qq_userinfo
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
     * QQopenid
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * QQopenid
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 用户ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qq_userinfo
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
        QqUserinfoEntity other = (QqUserinfoEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getGmtCreated() == null ? other.getGmtCreated() == null : this.getGmtCreated().equals(other.getGmtCreated()))
            && (this.getGmtUpdated() == null ? other.getGmtUpdated() == null : this.getGmtUpdated().equals(other.getGmtUpdated()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qq_userinfo
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
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }
}