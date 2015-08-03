package cn.gou23.cgodo.server.entity;

import java.io.Serializable;
import java.util.Date;

public class ClientRequestEntity implements Serializable {
    private String id;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 处理时间
     */
    private Long processingTime;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求浏览器
     */
    private String userAgent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table client_request
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 请求时间
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * 请求时间
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * 处理时间
     */
    public Long getProcessingTime() {
        return processingTime;
    }

    /**
     * 处理时间
     */
    public void setProcessingTime(Long processingTime) {
        this.processingTime = processingTime;
    }

    /**
     * 请求地址
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * 请求地址
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * 请求浏览器
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 请求浏览器
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
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
        ClientRequestEntity other = (ClientRequestEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getRequestTime() == null ? other.getRequestTime() == null : this.getRequestTime().equals(other.getRequestTime()))
            && (this.getProcessingTime() == null ? other.getProcessingTime() == null : this.getProcessingTime().equals(other.getProcessingTime()))
            && (this.getRequestUrl() == null ? other.getRequestUrl() == null : this.getRequestUrl().equals(other.getRequestUrl()))
            && (this.getUserAgent() == null ? other.getUserAgent() == null : this.getUserAgent().equals(other.getUserAgent()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getRequestTime() == null) ? 0 : getRequestTime().hashCode());
        result = prime * result + ((getProcessingTime() == null) ? 0 : getProcessingTime().hashCode());
        result = prime * result + ((getRequestUrl() == null) ? 0 : getRequestUrl().hashCode());
        result = prime * result + ((getUserAgent() == null) ? 0 : getUserAgent().hashCode());
        return result;
    }
}