package org.xezz.zeitabrechnung.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:50
 */
@Entity
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String name;
    private String description;
    @ManyToOne(optional = false)
    private Customer customer;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;
    // if a project gets deleted, all mapped timeframes will be deleted too, same for persisting
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Timeframe> timeframes = new HashSet<Timeframe>();

    /**
     * Default Constructor to support DI and JPA
     */
    public Project() {}

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Set<Timeframe> getTimeframes() {
        return timeframes;
    }

    public void setTimeframes(Set<Timeframe> timeframes) {
        this.timeframes = timeframes;
    }
}
