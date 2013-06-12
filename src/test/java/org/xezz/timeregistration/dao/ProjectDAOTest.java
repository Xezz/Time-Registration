package org.xezz.timeregistration.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.repository.CustomerRepository;

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
        final Long toAssert = projectId + 123L;
        dao.setProjectId(toAssert);
        assertNotEquals("Test set projectid", projectId, dao.getProjectId());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Test get name", name, dao.getName());
    }

    @Test
    public void testSetName() throws Exception {
        final String toAssert = name + "assert fail";
        dao.setName(toAssert);
        assertNotEquals("Test set name", name, dao.getName());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("Test get description", description, dao.getDescription());
    }

    @Test
    public void testSetDescription() throws Exception {
        final String toAssert = description + "assert fail";
        dao.setDescription(toAssert);
        assertNotEquals("Test set description", description, dao.getDescription());
    }

    @Test
    public void testGetCustomerId() throws Exception {
        assertEquals("Test get customer id", customerId, dao.getCustomerId());
    }

    @Test
    public void testSetCustomerId() throws Exception {
        final Long toAssert = customerId + 123L;
        dao.setCustomerId(toAssert);
        assertNotEquals("Test set customer id", customerId, dao.getCustomerId());
    }

    @Test
    public void testGetCreationDate() throws Exception {
        assertEquals("Test get creation date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testSetCreationDate() throws Exception {
        final Date toAssert = new Date(creationDate.getTime() + 10023);
        dao.setCreationDate(toAssert);
        assertNotEquals("Test set creation date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testGetLastUpdatedDate() throws Exception {
        assertEquals("Test get last updated date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testSetLastUpdatedDate() throws Exception {
        final Date toAssert = new Date(lastUpdatedDate.getTime() + 12314);
        dao.setLastUpdatedDate(toAssert);
        assertNotEquals("Test set last updated Date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testEquals() throws Exception {
        ProjectDAO toAssert = dao;
        assertTrue("Reference equality test", dao.equals(toAssert) && toAssert.equals(dao));
        toAssert = new ProjectDAO(projectId, customerId, name, description, creationDate, lastUpdatedDate);
        assertTrue("Constructor with same parameters equality test", dao.equals(toAssert) && toAssert.equals(dao));
    }

    @Test
    public void testNotEqualsToNull() throws Exception {
        assertFalse("Test obj does not equal to NULL", dao.equals(null));
    }

    @Test
    public void testNotEqualsWithDifferentValues() throws Exception {
        ProjectDAO toAssert = null;
        final Long failProjectId = projectId + 12312L;
        final Long failCustomerId = customerId + 113231L;
        final String failName = name + "failed name";
        final String failDescription = description + "fail description";
        final Date failCreationDate = new Date(creationDate.getTime() + 9876567);
        final Date failLastUpdatedDate = new Date(lastUpdatedDate.getTime() + 97867);

        toAssert = new ProjectDAO(failProjectId, customerId, name, description, creationDate, lastUpdatedDate);
        assertNotEquals("Constructor with same parameters equality test", dao, toAssert);

        toAssert = new ProjectDAO(projectId, failCustomerId, name, description, creationDate, lastUpdatedDate);
        assertNotEquals("Constructor with same parameters equality test", dao, toAssert);

        toAssert = new ProjectDAO(projectId, customerId, failName, description, creationDate, lastUpdatedDate);
        assertNotEquals("Constructor with same parameters equality test", dao, toAssert);

        toAssert = new ProjectDAO(projectId, customerId, name, failDescription, creationDate, lastUpdatedDate);
        assertNotEquals("Constructor with same parameters equality test", dao, toAssert);

        toAssert = new ProjectDAO(projectId, customerId, name, description, failCreationDate, lastUpdatedDate);
        assertNotEquals("Constructor with same parameters equality test", dao, toAssert);

        toAssert = new ProjectDAO(projectId, customerId, name, description, creationDate, failLastUpdatedDate);
        assertNotEquals("Constructor with same parameters equality test", dao, toAssert);
    }

    @Test
    public void testNotEqualsWithNullValues() throws Exception {
        ProjectDAO toAssert = null;
        final Long failProjectId = null;
        final Long failCustomerId = null;
        final String failName = null;
        final String failDescription = null;
        final Date failCreationDate = null;
        final Date failLastUpdatedDate = null;

        toAssert = new ProjectDAO(failProjectId, customerId, name, description, creationDate, lastUpdatedDate);
        assertNotEquals("Not equal with null value projectId", dao, toAssert);

        toAssert = new ProjectDAO(projectId, failCustomerId, name, description, creationDate, lastUpdatedDate);
        assertNotEquals("Not equal with null value failCustomerId", dao, toAssert);

        toAssert = new ProjectDAO(projectId, customerId, failName, description, creationDate, lastUpdatedDate);
        assertNotEquals("Not equal with null value failName", dao, toAssert);

        toAssert = new ProjectDAO(projectId, customerId, name, failDescription, creationDate, lastUpdatedDate);
        assertNotEquals("Not equal with null value failDescription", dao, toAssert);

        toAssert = new ProjectDAO(projectId, customerId, name, description, failCreationDate, lastUpdatedDate);
        assertNotEquals("Not equal with null value failCreationDate", dao, toAssert);

        toAssert = new ProjectDAO(projectId, customerId, name, description, creationDate, failLastUpdatedDate);
        assertNotEquals("Not equal with null value failLastUpdatedDate", dao, toAssert);
    }

    @Test
    public void testHashCode() throws Exception {
        ProjectDAO toAssert = dao;
        assertEquals("Reference hashcode test", dao.hashCode(), toAssert.hashCode());
        toAssert = new ProjectDAO(projectId, customerId, name, description, creationDate, lastUpdatedDate);
        assertEquals("Equal constructed objects hashcode test", dao.hashCode(), toAssert.hashCode());
    }

    @Test
    public void testHashCodeNotEqualWithDifferentValues() throws Exception {
        ProjectDAO toAssert = null;
        final Long failProjectId = projectId + 12312L;
        final Long failCustomerId = customerId + 113231L;
        final String failName = name + "failed name";
        final String failDescription = description + "fail description";
        final Date failCreationDate = new Date(creationDate.getTime() + 9876567);
        final Date failLastUpdatedDate = new Date(lastUpdatedDate.getTime() + 97867);

        toAssert = new ProjectDAO(failProjectId, customerId, name, description, creationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with different projectId", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, failCustomerId, name, description, creationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with different customerId", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, customerId, failName, description, creationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with different name", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, customerId, name, failDescription, creationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with different description", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, customerId, name, description, failCreationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with different creationDate", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, customerId, name, description, creationDate, failLastUpdatedDate);
        assertNotEquals("Hashcode test with different lastUpdatedDate", dao.hashCode(), toAssert.hashCode());
    }

    @Test
    public void testHashCodeNotEqualWithNullValues() throws Exception {
        ProjectDAO toAssert = null;
        final Long failProjectId = null;
        final Long failCustomerId = null;
        final String failName = null;
        final String failDescription = null;
        final Date failCreationDate = null;
        final Date failLastUpdatedDate = null;

        toAssert = new ProjectDAO(failProjectId, customerId, name, description, creationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with null projectId", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, failCustomerId, name, description, creationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with null customerId", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, customerId, failName, description, creationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with null name", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, customerId, name, failDescription, creationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with null description", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, customerId, name, description, failCreationDate, lastUpdatedDate);
        assertNotEquals("Hashcode test with null creationDate", dao.hashCode(), toAssert.hashCode());

        toAssert = new ProjectDAO(projectId, customerId, name, description, creationDate, failLastUpdatedDate);
        assertNotEquals("Hashcode test with null lastUpdatedDate", dao.hashCode(), toAssert.hashCode());
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
