package org.xezz.timeregistration.dao;

import java.util.Date;

/**
 * User: Xezz
 * Date: 30.05.13
 * Time: 22:50
 */
public class Coworker {
    private Long coworkerId;
    private String firstName;
    private String lastName;
    private Date creationDate;
    private Date lastUpdatedDate;

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
        if (!(o instanceof Coworker)) return false;

        Coworker coworker = (Coworker) o;

        if (coworkerId != null ? !coworkerId.equals(coworker.coworkerId) : coworker.coworkerId != null) return false;
        if (creationDate != null ? !creationDate.equals(coworker.creationDate) : coworker.creationDate != null)
            return false;
        if (!firstName.equals(coworker.firstName)) return false;
        if (!lastName.equals(coworker.lastName)) return false;
        if (lastUpdatedDate != null ? !lastUpdatedDate.equals(coworker.lastUpdatedDate) : coworker.lastUpdatedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coworkerId != null ? coworkerId.hashCode() : 0;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
        return result;
    }
}
