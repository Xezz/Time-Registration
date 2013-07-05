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

    public CustomerDAO(Customer c) {
        if (c == null) {
            throw new IllegalArgumentException("Customer must not be null");
        }
        this.customerId = c.getCustomerId();
        this.name = c.getName();
        this.customerInfo = c.getCustomerInfo();
        if (c.getCreationDate() != null) {
            this.creationDate = new Date(c.getCreationDate().getTime());
        }
        if (c.getLastUpdatedDate() != null) {
            this.lastUpdatedDate = new Date(c.getLastUpdatedDate().getTime());
        }
    }

    public CustomerDAO(Long customerId, String name, String customerInfo, Date creationDate, Date lastUpdatedDate) {
        this.customerId = customerId;
        this.name = name;
        this.customerInfo = customerInfo;
        if (creationDate != null) {
            this.creationDate = new Date(creationDate.getTime());
        }
        if (lastUpdatedDate != null) {
            this.lastUpdatedDate = new Date(lastUpdatedDate.getTime());
        }
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
        if (name != null) this.name = name;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        if(customerInfo != null) this.customerInfo = customerInfo;
    }

    public Date getCreationDate() {
        if (creationDate == null) {
            return null;
        }
        return new Date(creationDate.getTime());
    }

    public void setCreationDate(Date creationDate) {
        // FIXME: THROW EXP to not allow setting to null and fetch it
        if (this.creationDate != null && creationDate == null) {
            throw new IllegalArgumentException("Date must not be null");
        } else if (creationDate != null) {
            this.creationDate = new Date(creationDate.getTime());
        }
    }

    public Date getLastUpdatedDate() {
        if (lastUpdatedDate == null) {
            return null;
        }
        return new Date(lastUpdatedDate.getTime());
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        // only fail if there is a non null date already
        if (this.lastUpdatedDate != null && lastUpdatedDate == null) {
            throw new IllegalArgumentException("Date must not be null");
        } else if (lastUpdatedDate != null) {
            this.lastUpdatedDate = new Date(lastUpdatedDate.getTime());
        }
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
