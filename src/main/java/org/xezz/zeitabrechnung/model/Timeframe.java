package org.xezz.zeitabrechnung.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:53
 */
@Entity
public class Timeframe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeframeId;

    // Relevant data
    @ManyToOne(optional = false)
    private Project project;
    @ManyToOne(optional = false)
    private Coworker coworker;
    @Temporal(TemporalType.DATE)
    private Date startTime;
    @Temporal(TemporalType.DATE)
    private Date endTime;

    // "Auditing"
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;

    @PrePersist
    private void setDateBeforePersisting() {
        if (creationDate== null) creationDate = new Date();
        lastUpdatedDate = (Date) creationDate.clone();
    }

    @PreUpdate
    private void updateLastEditedDate() {
        lastUpdatedDate = new Date();
    }

    public Long getTimeframeId() {
        return timeframeId;
    }

    public void setTimeframeId(Long timeframeId) {
        this.timeframeId = timeframeId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Coworker getCoworker() {
        return coworker;
    }

    public void setCoworker(Coworker coworker) {
        this.coworker = coworker;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timeframe)) return false;

        Timeframe timeframe = (Timeframe) o;

        if (coworker != null ? !coworker.equals(timeframe.coworker) : timeframe.coworker != null) return false;
        if (creationDate != null ? !creationDate.equals(timeframe.creationDate) : timeframe.creationDate != null)
            return false;
        if (endTime != null ? !endTime.equals(timeframe.endTime) : timeframe.endTime != null) return false;
        if (lastUpdatedDate != null ? !lastUpdatedDate.equals(timeframe.lastUpdatedDate) : timeframe.lastUpdatedDate != null)
            return false;
        if (project != null ? !project.equals(timeframe.project) : timeframe.project != null) return false;
        if (startTime != null ? !startTime.equals(timeframe.startTime) : timeframe.startTime != null) return false;
        if (timeframeId != null ? !timeframeId.equals(timeframe.timeframeId) : timeframe.timeframeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeframeId != null ? timeframeId.hashCode() : 0;
        final int PRIME = 31;
        result = PRIME * result + (project != null ? project.hashCode() : 0);
        result = PRIME * result + (coworker != null ? coworker.hashCode() : 0);
        result = PRIME * result + (startTime != null ? startTime.hashCode() : 0);
        result = PRIME * result + (endTime != null ? endTime.hashCode() : 0);
        result = PRIME * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = PRIME * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
        return result;
    }
}
