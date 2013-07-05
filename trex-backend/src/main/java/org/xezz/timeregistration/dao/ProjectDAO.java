package org.xezz.timeregistration.dao;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.repository.CustomerRepository;

import java.util.Date;

/**
 * User: Xezz
 * Date: 30.05.13
 * Time: 22:50
 */
@Configurable(autowire = Autowire.BY_TYPE)
public class ProjectDAO {
    private Long projectId;
    private Long customerId;
    private String name;
    private String description;
    private Date creationDate;
    private Date lastUpdatedDate;


    CustomerRepository customerRepository;
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ProjectDAO() {
    }

    public ProjectDAO(Long projectId, Long customerId, String name, String description, Date creationDate, Date lastUpdatedDate) {
        this.projectId = projectId;
        this.customerId = customerId;
        this.name = name;
        this.description = description;
        if (creationDate != null) {
            this.creationDate = new Date(creationDate.getTime());
        }
        if (lastUpdatedDate != null) {
            this.lastUpdatedDate = new Date(lastUpdatedDate.getTime());
        }
    }

    public ProjectDAO(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }
        this.projectId = project.getProjectId();
        this.name = project.getName();
        this.description = project.getDescription();
        // FIXME: Should I thrown an exception here?
        if (project.getCustomer() != null) {
            this.customerId = project.getCustomer().getCustomerId();
        }
        if (project.getCreationDate() != null) {
            this.creationDate = new Date(project.getCreationDate().getTime());
        }
        if (project.getLastUpdatedDate() != null) {
            this.lastUpdatedDate = new Date(project.getLastUpdatedDate().getTime());
        }
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
        if (!(o instanceof ProjectDAO)) return false;

        ProjectDAO that = (ProjectDAO) o;

        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (lastUpdatedDate != null ? !lastUpdatedDate.equals(that.lastUpdatedDate) : that.lastUpdatedDate != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId != null ? projectId.hashCode() : 0;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
        return result;
    }

    public Customer receiveCustomer() {
        if (customerId == null) {
            return null;
        }
        return customerRepository.findOne(customerId);
    }
}
