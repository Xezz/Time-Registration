package org.xezz.zeitabrechnung.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 16:53
 */
@Entity
public class Coworker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coworkerId;
    private String givenName;
    private String surName;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coworker")
    private Set<Timeframe> timeframes = new HashSet<Timeframe>();
    /*
     * TODO: Consider adding credentials here too
     */

    /**
     * Default Constructor for JPA
     */
    public Coworker() {}

    /**
     * Set the creation date before this entity is going to be persisted
     */
    @PreUpdate
    private void setDateBeforePersisting() {
        creationDate = new Date();
        // Set the last updated Date to the creation date
        lastUpdatedDate = (Date) creationDate.clone();
    }

    /**
     * Update the time this coworker has last been updated
     */
    @PrePersist
    private void updateLastEditedDate() {
        lastUpdatedDate = new Date();
    }

    public Long getCoworkerId() {
        return coworkerId;
    }

    public void setCoworkerId(Long coworkerId) {
        this.coworkerId = coworkerId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Set<Timeframe> getTimeframes() {
        return timeframes;
    }

    public void setTimeframes(Set<Timeframe> timeframes) {
        this.timeframes = timeframes;
    }
}
