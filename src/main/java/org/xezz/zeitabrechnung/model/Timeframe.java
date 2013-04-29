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
}
