package org.xezz.timeregistration.model;

import org.xezz.timeregistration.dao.ProjectDAO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @ManyToOne
    private Customer customer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    /**
     * Default Constructor to support DI and JPA
     */
    public Project() {
    }

    public Project(Long projectId, String name, String description, Customer customer, Date creationDate, Date lastUpdatedDate) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.customer = customer;
        if (creationDate != null) {
            this.creationDate = new Date(creationDate.getTime());
        }
        if (lastUpdatedDate != null) {
            this.lastUpdatedDate = new Date(lastUpdatedDate.getTime());
        }
    }

    public Project(ProjectDAO p) {
        if (p == null) {
            throw new IllegalArgumentException("ProjectDAO must not be null");
        }
        this.projectId = p.getProjectId();
        this.name = p.getName();
        this.description = p.getDescription();
        if (p.getCreationDate() != null) {
            this.creationDate = new Date(p.getCreationDate().getTime());
        }
        if (p.getLastUpdatedDate() != null) {
            this.lastUpdatedDate = new Date(p.getLastUpdatedDate().getTime());
        }
        if (p.receiveCustomer() != null) {
            this.customer = p.receiveCustomer();
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
        if (creationDate == null) {
            return null;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (creationDate != null ? !creationDate.equals(project.creationDate) : project.creationDate != null)
            return false;
        if (customer != null ? !customer.equals(project.customer) : project.customer != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (lastUpdatedDate != null ? !lastUpdatedDate.equals(project.lastUpdatedDate) : project.lastUpdatedDate != null)
            return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (projectId != null ? !projectId.equals(project.projectId) : project.projectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId != null ? projectId.hashCode() : 0;
        final int prime = 31;
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (description != null ? description.hashCode() : 0);
        result = prime * result + (customer != null ? customer.hashCode() : 0);
        result = prime * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = prime * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);

        return result;
    }
}
