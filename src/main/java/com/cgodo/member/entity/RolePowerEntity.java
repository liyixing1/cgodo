package com.cgodo.member.entity;

import com.cgodo.constant.EnumStatus;
import java.io.Serializable;
import java.util.Date;

public class RolePowerEntity implements Serializable {
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
     * 角色ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String roleId;

    /**
     * 权限ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    private String powerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table role_power
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
     * 角色ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 权限ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public String getPowerId() {
        return powerId;
    }

    /**
     * 权限ID
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public void setPowerId(String powerId) {
        this.powerId = powerId == null ? null : powerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
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
        RolePowerEntity other = (RolePowerEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getGmtCreated() == null ? other.getGmtCreated() == null : this.getGmtCreated().equals(other.getGmtCreated()))
            && (this.getGmtUpdated() == null ? other.getGmtUpdated() == null : this.getGmtUpdated().equals(other.getGmtUpdated()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getPowerId() == null ? other.getPowerId() == null : this.getPowerId().equals(other.getPowerId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_power
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
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getPowerId() == null) ? 0 : getPowerId().hashCode());
        return result;
    }
}