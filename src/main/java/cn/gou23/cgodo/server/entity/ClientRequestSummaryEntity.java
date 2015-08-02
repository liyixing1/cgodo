package cn.gou23.cgodo.server.entity;

import java.io.Serializable;
import java.util.Date;

public class ClientRequestSummaryEntity implements Serializable {
    private String id;

    private Integer clientNumber;

    private Integer currentDayNumber;

    private Date summaryTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(Integer clientNumber) {
        this.clientNumber = clientNumber;
    }

    public Integer getCurrentDayNumber() {
        return currentDayNumber;
    }

    public void setCurrentDayNumber(Integer currentDayNumber) {
        this.currentDayNumber = currentDayNumber;
    }

    public Date getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(Date summaryTime) {
        this.summaryTime = summaryTime;
    }

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
        ClientRequestSummaryEntity other = (ClientRequestSummaryEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientNumber() == null ? other.getClientNumber() == null : this.getClientNumber().equals(other.getClientNumber()))
            && (this.getCurrentDayNumber() == null ? other.getCurrentDayNumber() == null : this.getCurrentDayNumber().equals(other.getCurrentDayNumber()))
            && (this.getSummaryTime() == null ? other.getSummaryTime() == null : this.getSummaryTime().equals(other.getSummaryTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientNumber() == null) ? 0 : getClientNumber().hashCode());
        result = prime * result + ((getCurrentDayNumber() == null) ? 0 : getCurrentDayNumber().hashCode());
        result = prime * result + ((getSummaryTime() == null) ? 0 : getSummaryTime().hashCode());
        return result;
    }
}