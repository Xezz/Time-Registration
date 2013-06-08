package org.xezz.timeregistration.dao;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * User: Xezz
 * Date: 08.06.13
 * Time: 22:04
 */
public class CustomerDAOTest {
    private final Long customerId = 123451337L;
    private final String name = "name";
    private final String customerInfo = "customerInfo";
    private final Date creationDate = new Date();
    private final Date lastUpdatedDate = new Date();
    private CustomerDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new CustomerDAO(customerId, name, customerInfo, creationDate, lastUpdatedDate);
    }

    @Test
    public void testGetCustomerId() throws Exception {
        assertEquals("Get customer ID equals", customerId, dao.getCustomerId());
    }

    @Test
    public void testSetCustomerId() throws Exception {
        fail();
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Get name", name, dao.getName());
    }

    @Test
    public void testSetName() throws Exception {
        fail();
    }

    @Test
    public void testGetCustomerInfo() throws Exception {
        assertEquals("Get customer info", customerInfo, dao.getCustomerInfo());
    }

    @Test
    public void testSetCustomerInfo() throws Exception {
        fail();
    }

    @Test
    public void testGetCreationDate() throws Exception {
        assertEquals("Get creation date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testSetCreationDate() throws Exception {
        fail();
    }

    @Test
    public void testGetLastUpdatedDate() throws Exception {
        assertEquals("Get last updated date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testSetLastUpdatedDate() throws Exception {
        fail();
    }

    @Test
    public void testEquals() throws Exception {
        fail();
    }

    @Test
    public void testHashCode() throws Exception {
        fail();
    }
}
