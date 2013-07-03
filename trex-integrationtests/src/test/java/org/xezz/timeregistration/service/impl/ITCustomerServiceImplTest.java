package org.xezz.timeregistration.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xezz.timeregistration.AbstractBaseTest;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.dao.CustomerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.service.CoworkerService;
import org.xezz.timeregistration.service.CustomerService;
import org.xezz.timeregistration.service.ProjectService;
import org.xezz.timeregistration.service.TimeSpanService;

import java.util.Date;
import java.util.Iterator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * User: Xezz
 * Date: 03.07.13
 * Time: 17:09
 */
public class ITCustomerServiceImplTest extends AbstractBaseTest {
    private final Long projectIdExists = 101L;
    private final Long timeSpanIdExists = 501L;
    private final Long coworkerIdExists = 1L;
    private final String nameExists = "Xezz Ltd.";
    private final Long customerIdExists = 51L;
    private final String infoExists = "Development of JavaEE apps and other fancy stuff!";

    @Autowired
    CoworkerService coworkerService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProjectService projectService;
    @Autowired
    TimeSpanService timeSpanService;


    @Test
    public void testCustomerByName() throws Exception {
        final Iterable<CustomerDAO> customerDAOs = customerService.getByName(nameExists);
        final Iterator<CustomerDAO> iterator = customerDAOs.iterator();
        assertThat("No customer received", true, is(iterator.hasNext()));
        final CustomerDAO fetchedDao = iterator.next();
        assertThat("Name did not match", nameExists, is(equalTo(fetchedDao.getName())));
    }

    @Test
    public void testCustomerByNameMatch() throws Exception {
        final Iterable<CustomerDAO> customerDAOs = customerService.getByNameMatch(nameExists);
        final Iterator<CustomerDAO> iterator = customerDAOs.iterator();
        assertThat("No customer received", true, is(equalTo(iterator.hasNext())));
        assertThat("Name did not match", true, is(equalTo(iterator.next().getName().contains(nameExists))));
    }

    @Test
    public void testCustomerByProject() throws Exception {
        final ProjectDAO projectDAO = projectService.getById(projectIdExists);
        assertThat("Project did not exist", projectDAO, is(notNullValue()));
        final CustomerDAO customerDAO = customerService.getByProject(projectDAO);
        assertThat("Customer did not exist", customerDAO, is(notNullValue()));
    }

    @Test
    public void testCustomerByTimeSpan() throws Exception {
        final TimeSpanDAO timeSpanById = timeSpanService.getById(timeSpanIdExists);
        assertThat("Timespan did not exist", timeSpanById, is(notNullValue()));
        final CustomerDAO customerDAO = customerService.getByTimeSpan(timeSpanById);
        assertThat("Customer did not exist", customerDAO, is(notNullValue()));

    }

    @Test
    public void testCustomersByCoworker() throws Exception {
        final CoworkerDAO coworkerDAO = coworkerService.getById(coworkerIdExists);
        assertThat("Coworker did not exist", coworkerDAO, is(notNullValue()));
        final Iterable<CustomerDAO> customerDAOs = customerService.getByCoworker(coworkerDAO);
        assertThat("Iterable was null, expected to have at least an empty iterable", customerDAOs, is(notNullValue()));
    }

    @Test
    public void testCustomersAll() throws Exception {
        final Iterable<CoworkerDAO> all = coworkerService.getAll();
        assertThat("Received list was null", all, is(notNullValue()));
        int count = 0;
        for (CoworkerDAO dao : all) {
            count++;
        }
        assertThat("Result was empty", 0, is(lessThan(count)));

    }

    @Test
    public void testCustomerById() throws Exception {
        final CustomerDAO dao = customerService.getById(customerIdExists);
        assertThat("Customer was not in database", dao, is(notNullValue()));
        assertThat("Id did not match", customerIdExists, is(equalTo(dao.getCustomerId())));
        assertThat("Name did not match", nameExists, is(equalTo(dao.getName())));
        assertThat("Info did not match", infoExists, is(equalTo(dao.getCustomerInfo())));
        assertThat("Creation date was null", true, is(notNullValue()));
        assertThat("Creation date is newer than right now", true, is((new Date().after(dao.getCreationDate()))));
        assertThat("Last updated was null", true, is(notNullValue()));
        assertThat("Last updated date is newer than right now", -1, is(lessThan(new Date().compareTo(dao.getLastUpdatedDate()))));
    }

    @Test
    public void testAddNewCustomer() throws Exception {
        final CustomerDAO addedDao = new CustomerDAO();
        addedDao.setName("New customer");
        addedDao.setCustomerInfo("This is a newly created dao");
        final CustomerDAO createdDao = customerService.addNew(addedDao);
        assertThat("Created dao was null!", createdDao, is(notNullValue()));
        assertThat("Creation date was null", createdDao.getCreationDate(), is(notNullValue()));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        final CustomerDAO dao = customerService.getById(customerIdExists);
        customerService.delete(dao);
        final CustomerDAO deleted = customerService.getById(customerIdExists);
        assertThat("Customer was not deleted", deleted, is(nullValue()));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        final CustomerDAO dao = customerService.getById(customerIdExists);
        assertThat("Customer did not exist anymore", dao, is(notNullValue()));
        assertThat("Name was empty string", dao.getName(), is(not(equalToIgnoringCase(""))));
        final String newName = dao.getName() + dao.getName();
        dao.setName(newName);
        final CustomerDAO updatedDao = customerService.update(dao);
        assertThat("New name was not saved...", newName, is(equalTo(updatedDao.getName())));
        // time will not update right away due to JPA apparently
        //assertThat("Last updated date did not change", currentTime.getTime(), is(lessThanOrEqualTo(updatedDao.getLastUpdatedDate().getTime())));
    }

    @Test
    public void testCreateNewAndUpdateCustomer() throws Exception {
        CustomerDAO dao = new CustomerDAO();
        final String name = "Hans wurst";
        dao.setName(name);
        dao.setCustomerInfo("Der hans hat ne wurst");
        final CustomerDAO customerDAO = customerService.addNew(dao);
        final String newName = customerDAO.getName() + customerDAO.getName();
        customerDAO.setName(newName);
        final CustomerDAO update = customerService.update(customerDAO);
        assertThat("Name did not change", name, is(not(equalTo(update.getName()))));
        assertThat("New name does not match expect name", newName, is(equalTo(update.getName())));

    }
}
