package org.xezz.zeitabrechnung.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:53
 */
@Entity
public class Timeframe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeframeId;
    @ManyToOne(optional = false)
    private Project project;
    @ManyToOne(optional = false)
    private Coworker coworker;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;

    @PreUpdate
    public void setCreationDate() {
        if (this.creationDate == null) {
            this.creationDate = new Date();
        }
        this.lastUpdatedDate = (Date) creationDate.clone();
    }

    @PrePersist
    public void setLastUpdatedDate() {
        this.lastUpdatedDate = new Date();
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
}
