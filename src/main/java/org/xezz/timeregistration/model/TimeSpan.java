package org.xezz.timeregistration.model;

import org.xezz.timeregistration.dao.TimeSpanDAO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:53
 */
@Entity
public class TimeSpan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeSpanId;

    // Relevant data
    @ManyToOne(optional = false)
    private Project project;
    @ManyToOne(optional = false)
    private Coworker coworker;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    private Long durationInMinutes;


    // "Auditing"
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    public TimeSpan() {
    }

    public TimeSpan(TimeSpanDAO t) {
        if (t == null) {
            throw new IllegalArgumentException("TimeSpanDAO must not be null");
        }
        this.timeSpanId = t.getTimeSpanId();
        this.project = t.receiveProject();
        this.coworker = t.receiveCoworker();
        if (t.getStartTime() != null) {
            this.startTime = new Date(t.getStartTime().getTime());
        }
        this.durationInMinutes = t.getDurationInMinutes();
        if (t.getCreationDate() != null) {
            this.creationDate = new Date(t.getCreationDate().getTime());
        }
        if (t.getLastUpdatedDate() != null) {
            this.lastUpdatedDate = new Date(t.getLastUpdatedDate().getTime());
        }
    }

    @PrePersist
    private void setDateBeforePersisting() {
        lastUpdatedDate = new Date();
        creationDate = new Date(lastUpdatedDate.getTime());
    }

    @PreUpdate
    private void updateLastEditedDate() {
        lastUpdatedDate = new Date();
    }

    public Long getTimeSpanId() {
        return timeSpanId;
    }

    public void setTimeSpanId(Long timeSpanId) {
        this.timeSpanId = timeSpanId;
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
        return new Date(creationDate.getTime());
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("Date must not be null");
        }
        this.creationDate = new Date(creationDate.getTime());
    }

    public Date getLastUpdatedDate() {
        if (lastUpdatedDate == null) {
            return null;
        }
        return new Date(lastUpdatedDate.getTime());
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        if (lastUpdatedDate == null) {
            throw new IllegalArgumentException("Date must not be null");
        }
        this.lastUpdatedDate = new Date(lastUpdatedDate.getTime());
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        if (startTime == null) {
            throw new IllegalArgumentException("Date must not be null");
        }
        this.startTime = new Date(startTime.getTime());
    }

    public Long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSpan)) return false;

        TimeSpan timeSpan = (TimeSpan) o;

        if (coworker != null ? !coworker.equals(timeSpan.coworker) : timeSpan.coworker != null) return false;
        if (creationDate != null ? !creationDate.equals(timeSpan.creationDate) : timeSpan.creationDate != null)
            return false;
        if (durationInMinutes != null ? !durationInMinutes.equals(timeSpan.durationInMinutes) : timeSpan.durationInMinutes != null)
            return false;
        if (lastUpdatedDate != null ? !lastUpdatedDate.equals(timeSpan.lastUpdatedDate) : timeSpan.lastUpdatedDate != null)
            return false;
        if (project != null ? !project.equals(timeSpan.project) : timeSpan.project != null) return false;
        if (startTime != null ? !startTime.equals(timeSpan.startTime) : timeSpan.startTime != null) return false;
        if (timeSpanId != null ? !timeSpanId.equals(timeSpan.timeSpanId) : timeSpan.timeSpanId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeSpanId != null ? timeSpanId.hashCode() : 0;
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (coworker != null ? coworker.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (durationInMinutes != null ? durationInMinutes.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
        return result;
    }
}
