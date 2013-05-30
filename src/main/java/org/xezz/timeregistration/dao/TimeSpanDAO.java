package org.xezz.timeregistration.dao;

import java.util.Date;

/**
 * User: Xezz
 * Date: 30.05.13
 * Time: 22:50
 */
public class TimeSpanDAO {
    private Long timeSpanId;
    private Long projectId;
    private Long coworkerId;
    private Date startTime;
    private Date endTime;
    private Date creationDate;
    private Date lastUpdatedDate;

    public Long getTimeSpanId() {
        return timeSpanId;
    }

    public void setTimeSpanId(Long timeSpanId) {
        this.timeSpanId = timeSpanId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getCoworkerId() {
        return coworkerId;
    }

    public void setCoworkerId(Long coworkerId) {
        this.coworkerId = coworkerId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSpanDAO)) return false;

        TimeSpanDAO timeSpan = (TimeSpanDAO) o;

        if (!coworkerId.equals(timeSpan.coworkerId)) return false;
        if (creationDate != null ? !creationDate.equals(timeSpan.creationDate) : timeSpan.creationDate != null)
            return false;
        if (!endTime.equals(timeSpan.endTime)) return false;
        if (lastUpdatedDate != null ? !lastUpdatedDate.equals(timeSpan.lastUpdatedDate) : timeSpan.lastUpdatedDate != null)
            return false;
        if (!projectId.equals(timeSpan.projectId)) return false;
        if (!startTime.equals(timeSpan.startTime)) return false;
        if (timeSpanId != null ? !timeSpanId.equals(timeSpan.timeSpanId) : timeSpan.timeSpanId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeSpanId != null ? timeSpanId.hashCode() : 0;
        result = 31 * result + projectId.hashCode();
        result = 31 * result + coworkerId.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
        return result;
    }
}
