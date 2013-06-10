package org.xezz.timeregistration.dao;

import org.springframework.beans.factory.annotation.Configurable;
import org.xezz.timeregistration.model.Customer;

import java.util.Date;

/**
 * User: Xezz
 * Date: 30.05.13
 * Time: 22:50
 */
@Configurable
public class CustomerDAO {
    private Long customerId;
    private String name;
    private String customerInfo;
    private Date creationDate;
    private Date lastUpdatedDate;

    public CustomerDAO() {
    }

    public CustomerDAO(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.name = customer.getName();
        this.customerInfo = customer.getCustomerInfo();
        this.creationDate = customer.getCreationDate();
        this.lastUpdatedDate = customer.getLastUpdatedDate();
    }

    public CustomerDAO(Long customerId, String name, String customerInfo, Date creationDate, Date lastUpdatedDate) {
        this.creationDate = creationDate;
        this.customerId = customerId;
        this.customerInfo = customerInfo;
        this.lastUpdatedDate = lastUpdatedDate;
        this.name = name;
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
        if (!(o instanceof CustomerDAO)) return false;

        CustomerDAO that = (CustomerDAO) o;

        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (customerInfo != null ? !customerInfo.equals(that.customerInfo) : that.customerInfo != null) return false;
        if (lastUpdatedDate != null ? !lastUpdatedDate.equals(that.lastUpdatedDate) : that.lastUpdatedDate != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (customerInfo != null ? customerInfo.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
        return result;
    }
}
