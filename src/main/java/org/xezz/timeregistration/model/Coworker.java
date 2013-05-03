package org.xezz.timeregistration.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coworker)) return false;

        Coworker coworker = (Coworker) o;

        if (coworkerId != null ? !coworkerId.equals(coworker.coworkerId) : coworker.coworkerId != null) return false;
        if (creationDate != null ? !creationDate.equals(coworker.creationDate) : coworker.creationDate != null)
            return false;
        if (firstName != null ? !firstName.equals(coworker.firstName) : coworker.firstName != null) return false;
        if (lastName != null ? !lastName.equals(coworker.lastName) : coworker.lastName != null) return false;
        if (lastUpdatedDate != null ? !lastUpdatedDate.equals(coworker.lastUpdatedDate) : coworker.lastUpdatedDate != null)
            return false;
        if (timeframes != null ? !timeframes.equals(coworker.timeframes) : coworker.timeframes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coworkerId != null ? coworkerId.hashCode() : 0;
        final int PRIME = 31;
        result = PRIME * result + (firstName != null ? firstName.hashCode() : 0);
        result = PRIME * result + (lastName != null ? lastName.hashCode() : 0);
        result = PRIME * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = PRIME * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
        result = PRIME * result + (timeframes != null ? timeframes.hashCode() : 0);
        return result;
    }
}
