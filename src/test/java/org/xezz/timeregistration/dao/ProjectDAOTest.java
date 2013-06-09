package org.xezz.timeregistration.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.repositories.CustomerRepository;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * User: bkoch
 * Date: 09.06.13
 * Time: 17:03
 */
public class ProjectDAOTest {
    private final Long projectId = 123456L;
    private final Long customerId = 987654L;
    private final String name = "name";
    private final String description = "description";
    private final Date creationDate = new Date();
    private final Date lastUpdatedDate = new Date();
    private ProjectDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new ProjectDAO(projectId, customerId, name, description, creationDate, lastUpdatedDate);
        CustomerRepository mockRepo = Mockito.mock(CustomerRepository.class);
        Customer mockCustomer = Mockito.mock(Customer.class);
        when(mockCustomer.getCustomerId()).thenReturn(customerId);
        when(mockRepo.findOne(customerId)).thenReturn(mockCustomer);
        dao.customerRepository = mockRepo;
    }

    @Test
    public void testGetProjectId() throws Exception {
        assertEquals("Test get projectId", projectId, dao.getProjectId());
    }

    @Test
    public void testSetProjectId() throws Exception {
        final Long assertId = projectId + 123L;
        dao.setProjectId(assertId);
        assertNotEquals("Test set projectid", projectId, dao.getProjectId());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Test get name", name, dao.getName());
    }

    @Test
    public void testSetName() throws Exception {
        final String assertName = name + "assert fail";
        dao.setName(assertName);
        assertNotEquals("Test set name", name, dao.getName());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("Test get description", description, dao.getDescription());
    }

    @Test
    public void testSetDescription() throws Exception {
        final String assertDescription = description + "assert fail";
        dao.setDescription(assertDescription);
        assertNotEquals("Test set description", description, dao.getDescription());
    }

    @Test
    public void testGetCustomerId() throws Exception {
        assertEquals("Test get customer id", customerId, dao.getCustomerId());
    }

    @Test
    public void testSetCustomerId() throws Exception {
        final Long assertId = customerId + 123L;
        dao.setCustomerId(assertId);
        assertNotEquals("Test set customer id", customerId, dao.getCustomerId());
    }

    @Test
    public void testGetCreationDate() throws Exception {
        assertEquals("Test get creation date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testSetCreationDate() throws Exception {
        final Date assertDate = new Date(creationDate.getTime() + 10023);
        dao.setCreationDate(assertDate);
        assertNotEquals("Test set creation date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testGetLastUpdatedDate() throws Exception {
        assertEquals("Test get last updated date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testSetLastUpdatedDate() throws Exception {
        final Date assertDate = new Date(lastUpdatedDate.getTime() + 12314);
        dao.setLastUpdatedDate(assertDate);
        assertNotEquals("Test set last updated Date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testEquals() throws Exception {
        ProjectDAO assertDao = dao;
        assertTrue("Reference equality test", dao.equals(assertDao) && assertDao.equals(dao));
        assertDao = new ProjectDAO(projectId, customerId, name, description, creationDate, lastUpdatedDate);
        assertTrue("Constructor with same parameters equality test", dao.equals(assertDao) && assertDao.equals(dao));
    }

    @Test
    public void testHashCode() throws Exception {
        ProjectDAO assertDao = dao;
        assertEquals("Reference hashcode test", dao.hashCode(), assertDao.hashCode());
        assertDao = new ProjectDAO(projectId, customerId, name, description, creationDate, lastUpdatedDate);
        assertEquals("Reference hashcode test", dao.hashCode(), assertDao.hashCode());
    }

    @Test
    public void testReceiveCustomer() throws Exception {
        Customer customer = dao.receiveCustomer();
        assertNotNull("Test receiving Customer", customer);
    }

    @Test
    public void testReceivedCustomerHasSameId() throws Exception {
        Customer customer = dao.receiveCustomer();
        assertEquals("Testing the id to be equal", customerId, customer.getCustomerId());
    }
}
