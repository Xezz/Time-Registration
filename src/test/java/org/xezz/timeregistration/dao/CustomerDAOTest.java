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
        final Long toAssert = customerId + 1234;
        dao.setCustomerId(toAssert);
        assertEquals("Set customer ID", toAssert, dao.getCustomerId());
        assertNotEquals("Set customer ID changed ID", customerId, dao.getCustomerId());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Get name", name, dao.getName());
    }

    @Test
    public void testSetName() throws Exception {
        final String toAssert = name + "assert added";
        dao.setName(toAssert);
        assertEquals("Set customer name", toAssert, dao.getName());
        assertNotEquals("Set customer name changed name", name, dao.getName());
    }

    @Test
    public void testGetCustomerInfo() throws Exception {
        assertEquals("Get customer info", customerInfo, dao.getCustomerInfo());
    }

    @Test
    public void testSetCustomerInfo() throws Exception {
        final String toAssert = customerInfo + "assert added";
        dao.setCustomerInfo(toAssert);
        assertEquals("Set customer info", toAssert, dao.getCustomerInfo());
        assertNotEquals("Set customer info changed info", customerId, dao.getCustomerInfo());
    }

    @Test
    public void testGetCreationDate() throws Exception {
        assertEquals("Get creation date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testSetCreationDate() throws Exception {
        final Date toAssert = new Date(creationDate.getTime() + 12345);
        dao.setCreationDate(toAssert);
        assertEquals("Set creation date", toAssert, dao.getCreationDate());
        assertNotEquals("Set creation date changed Date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testGetLastUpdatedDate() throws Exception {
        assertEquals("Get last updated date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testSetLastUpdatedDate() throws Exception {
        final Date toAssert = new Date(dao.getLastUpdatedDate().getTime() + 213131);
        dao.setLastUpdatedDate(toAssert);
        assertEquals("Set last updated Date", toAssert, dao.getLastUpdatedDate());
        assertNotEquals("Set last updated Date changed Date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testEquals() throws Exception {
        CustomerDAO toAssert = dao;
        assertTrue("Reference to the same object is equal", dao.equals(toAssert) && toAssert.equals(dao));
        toAssert = new CustomerDAO(customerId, name, customerInfo, creationDate, lastUpdatedDate);
        assertTrue("Two objects created with the same arguments are equal", dao.equals(toAssert) && toAssert.equals(dao));
    }

    @Test
    public void testNotEqualsNull() throws Exception {
        assertFalse("Instantiated object is not equal to NULL", dao.equals(null));
    }

    @Test
    public void testHashCode() throws Exception {
        CustomerDAO toAssert = dao;
        assertEquals("Refernece to the same object by two objects has the same hascode", toAssert.hashCode(), dao.hashCode());
        toAssert = new CustomerDAO(customerId, name, customerInfo, creationDate, lastUpdatedDate);
        assertEquals("Created with the same argument same hashcode", toAssert.hashCode(), dao.hashCode());
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
    public void testEqualsFailsWithNullValues() throws Exception {
        assertNotEquals("Not equal with different id", new CustomerDAO(null, name, customerInfo, creationDate, lastUpdatedDate), dao);
        assertNotEquals("Not equal with different name", new CustomerDAO(customerId, null, customerInfo, creationDate, lastUpdatedDate), dao);
        assertNotEquals("Not equal with different info", new CustomerDAO(customerId, name, null, creationDate, lastUpdatedDate), dao);
        assertNotEquals("Not equal with different creation date", new CustomerDAO(customerId, name, customerInfo, null, lastUpdatedDate), dao);
        assertNotEquals("Not equal with different last updated date", new CustomerDAO(customerId, name, customerInfo, creationDate, null), dao);
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

    @Test
    public void testHashCodeFailsWithNullValues() throws Exception {
        assertNotEquals("Different hashcodes with different id", new CustomerDAO(null, name, customerInfo, creationDate, lastUpdatedDate).hashCode(), dao.hashCode());
        assertNotEquals("Different hashcodes with different name", new CustomerDAO(customerId, null, customerInfo, creationDate, lastUpdatedDate).hashCode(), dao.hashCode());
        assertNotEquals("Different hashcodes with different info", new CustomerDAO(customerId, name, null, creationDate, lastUpdatedDate).hashCode(), dao.hashCode());
        assertNotEquals("Different hashcodes with different creation date", new CustomerDAO(customerId, name, customerInfo, null, lastUpdatedDate), dao.hashCode());
        assertNotEquals("Different hashcodes with different last updated date", new CustomerDAO(customerId, name, customerInfo, creationDate, null).hashCode(), dao.hashCode());
    }
}
