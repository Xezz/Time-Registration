package org.xezz.timeregistration.dao;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

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
        final Long assertId = customerId + 1234;
        dao.setCustomerId(assertId);
        assertEquals("Set customer ID", assertId, dao.getCustomerId());
        assertNotEquals("Set customer ID changed ID", customerId, dao.getCustomerId());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Get name", name, dao.getName());
    }

    @Test
    public void testSetName() throws Exception {
        final String assertName = name + "assert added";
        dao.setName(assertName);
        assertEquals("Set customer name", assertName, dao.getName());
        assertNotEquals("Set customer name changed name", name, dao.getName());
    }

    @Test
    public void testGetCustomerInfo() throws Exception {
        assertEquals("Get customer info", customerInfo, dao.getCustomerInfo());
    }

    @Test
    public void testSetCustomerInfo() throws Exception {
        final String assertCustomerInfo = customerInfo + "assert added";
        dao.setCustomerInfo(assertCustomerInfo);
        assertEquals("Set customer info", assertCustomerInfo, dao.getCustomerInfo());
        assertNotEquals("Set customer info changed info", customerId, dao.getCustomerInfo());
    }

    @Test
    public void testGetCreationDate() throws Exception {
        assertEquals("Get creation date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testSetCreationDate() throws Exception {
        final Date assertDate = new Date(creationDate.getTime() + 12345);
        dao.setCreationDate(assertDate);
        assertEquals("Set creation date", assertDate, dao.getCreationDate());
        assertNotEquals("Set creation date changed Date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testGetLastUpdatedDate() throws Exception {
        assertEquals("Get last updated date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testSetLastUpdatedDate() throws Exception {
        final Date assertDate = new Date(dao.getLastUpdatedDate().getTime() + 213131);
        dao.setLastUpdatedDate(assertDate);
        assertEquals("Set last updated Date", assertDate, dao.getLastUpdatedDate());
        assertNotEquals("Set last updated Date changed Date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testEquals() throws Exception {
        CustomerDAO compareDao = dao;
        assertTrue("Reference to the same object is equal", dao.equals(compareDao) && compareDao.equals(dao));
        compareDao = new CustomerDAO(customerId, name, customerInfo, creationDate, lastUpdatedDate);
        assertTrue("Two objects created with the same arguments are equal", dao.equals(compareDao) && compareDao.equals(dao));
    }

    @Test
    public void testHashCode() throws Exception {
        CustomerDAO compareDao = dao;
        assertEquals("Refernece to the same object by two objects has the same hascode", compareDao.hashCode(), dao.hashCode());
        compareDao = new CustomerDAO(customerId, name, customerInfo, creationDate, lastUpdatedDate);
        assertEquals("Created with the same argument same hashcode", compareDao.hashCode(), dao.hashCode());
    }

    @Test
    public void testEqualsFails() throws Exception {
        final Long failCustomerId = dao.getCustomerId() + 1234;
        final String failName = dao.getName() + " fail name";
        final String failCustomerInfo = dao.getCustomerInfo() + " fail customer info";
        final Date failCreationDate = new Date(creationDate.getTime() + 1234332);
        final Date failLastUpdatedDate = new Date(lastUpdatedDate.getTime() + 123131);
        assertNotEquals("Not equal with different id", new CustomerDAO(failCustomerId, name, customerInfo, creationDate, lastUpdatedDate), dao);
        assertNotEquals("Not equal with different name", new CustomerDAO(customerId, failName, customerInfo, creationDate, lastUpdatedDate), dao);
        assertNotEquals("Not equal with different info", new CustomerDAO(customerId, name, failCustomerInfo, creationDate, lastUpdatedDate), dao);
        assertNotEquals("Not equal with different creation date", new CustomerDAO(customerId, name, customerInfo, failCreationDate, lastUpdatedDate), dao);
        assertNotEquals("Not equal with different last updated date", new CustomerDAO(customerId, name, customerInfo, creationDate, failLastUpdatedDate), dao);
    }

    @Test
    public void testHashCodeFails() throws Exception {
        final Long failCustomerId = dao.getCustomerId() + 1234;
        final String failName = dao.getName() + " fail name";
        final String failCustomerInfo = dao.getCustomerInfo() + " fail customer info";
        final Date failCreationDate = new Date(creationDate.getTime() + 1234332);
        final Date failLastUpdatedDate = new Date(lastUpdatedDate.getTime() + 123131);
        assertNotEquals("Different hashcodes with different id", new CustomerDAO(failCustomerId, name, customerInfo, creationDate, lastUpdatedDate).hashCode(), dao.hashCode());
        assertNotEquals("Different hashcodes with different name", new CustomerDAO(customerId, failName, customerInfo, creationDate, lastUpdatedDate).hashCode(), dao.hashCode());
        assertNotEquals("Different hashcodes with different info", new CustomerDAO(customerId, name, failCustomerInfo, creationDate, lastUpdatedDate).hashCode(), dao.hashCode());
        assertNotEquals("Different hashcodes with different creation date", new CustomerDAO(customerId, name, customerInfo, failCreationDate, lastUpdatedDate), dao.hashCode());
        assertNotEquals("Different hashcodes with different last updated date", new CustomerDAO(customerId, name, customerInfo, creationDate, failLastUpdatedDate).hashCode(), dao.hashCode());
    }
}
