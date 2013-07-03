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
    private final String descriptionExists = "Development of JavaEE apps and other fancy stuff!";

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
        assertThat("Iterable was null, expected to have atleast an empty iterable", customerDAOs, is(notNullValue()));
    }

    @Test
    public void testCustomersAll() throws Exception {

    }

    @Test
    public void testCustomerById() throws Exception {

    }

    @Test
    public void testAddNewCustomer() throws Exception {

    }

    @Test
    public void testUpdateCustomer() throws Exception {

    }

    @Test
    public void testDeleteCustomer() throws Exception {

    }
}
