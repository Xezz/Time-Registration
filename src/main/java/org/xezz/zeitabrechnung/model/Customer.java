package org.xezz.zeitabrechnung.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:44
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String name;
    private String customerInfo;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;

    /**
     * Default constructor for JPA/Spring/Whatever
     */
    public Customer() {}

    /**
     * Set the date of creation to the current time
     */
    @PreUpdate
    private void setCreationDate() {
        this.creationDate = new Date();
        // Set the last updated date to the creation date
        this.lastUpdatedDate = (Date) creationDate.clone();
    }

    /**
     * Update the date of last modification to the current time
     */
    @PrePersist
    private void setLastUpdatedDate() {
        this.lastUpdatedDate = new Date();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

}
