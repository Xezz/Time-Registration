package org.xezz.zeitabrechnung.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 16:53
 */
@Entity
public class Coworker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coworkerId;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;
    // Delete, Persist etc by cascading
    // mapped by the field coworker in Timeframe
    // fetch the set when populating this entity
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coworker", fetch = FetchType.EAGER)
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
    @PrePersist
    private void setDateBeforePersisting() {
        creationDate = new Date();
        // Set the last updated Date to the creation date
        lastUpdatedDate = (Date) creationDate.clone();
    }

    /**
     * Update the time this coworker has last been updated
     */
    @PreUpdate
    private void updateLastEditedDate() {
        lastUpdatedDate = new Date();
    }

    public Long getCoworkerId() {
        return coworkerId;
    }

    public void setCoworkerId(Long coworkerId) {
        this.coworkerId = coworkerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
