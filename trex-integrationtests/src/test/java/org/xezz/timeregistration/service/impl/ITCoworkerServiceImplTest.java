package org.xezz.timeregistration.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xezz.timeregistration.AbstractBaseITConfiguration;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.service.CoworkerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * User: bkoch
 * Date: 08.06.13
 * Time: 13:59
 */

public class ITCoworkerServiceImplTest extends AbstractBaseITConfiguration {

    // Default coworkerId that we know
    private final Long coworkerId = 1L;
    // Default coworkerId we know and we want to delete
    private final Long idOfCoworkerToDelete = 401L;
    // First name of the default Coworker
    private final String firstName = "Bastian";
    // Last name of the default Coworker
    private final String lastName = "Koch";
    @Autowired
    private CoworkerService coworkerService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCoworkersAll() throws Exception {
        assertNotNull("Service has not been injected", coworkerService);
        assertNotNull("service.getAll returned null", coworkerService.getAll());

    }

    @Test
    public void testCoworkersByFirstName() throws Exception {
        final Iterable<CoworkerDAO> coworkerDAOs = coworkerService.getByFirstName(firstName);
        assertNotNull("Received Iterable was null", coworkerDAOs);
        final Collection<CoworkerDAO> daoCollection = new ArrayList<CoworkerDAO>();
        for (CoworkerDAO c : coworkerDAOs) {
            daoCollection.add(c);
        }
        assertThat("Result does not have any entity", daoCollection.size(), is(greaterThan(0)));
        for (CoworkerDAO c : coworkerDAOs) {
            assertThat("First name was not " + firstName, firstName, is(c.getFirstName()));
        }
    }

    @Test
    public void testCoworkersByLastName() throws Exception {
        final Iterable<CoworkerDAO> daos = coworkerService.getByLastName(lastName);
        assertNotNull("Received Iterable was null", daos);
        final Collection<CoworkerDAO> daoCollection = new ArrayList<CoworkerDAO>();
        for (CoworkerDAO c : daos) {
            daoCollection.add(c);
        }
        assertThat("Result does not have any entity", daoCollection.size(), is(greaterThan(0)));
        for (CoworkerDAO c : daos) {
            assertThat("Last name was not " + lastName, lastName, is(c.getLastName()));
        }
    }

    @Test
    public void testCoworkersByFirstAndLastName() throws Exception {
        final Iterable<CoworkerDAO> daos = coworkerService.getByFirstAndLastName(firstName, lastName);
        assertNotNull("Received Iterable was null", daos);
        final Collection<CoworkerDAO> daoCollection = new ArrayList<CoworkerDAO>();
        for (CoworkerDAO c : daos) {
            daoCollection.add(c);
        }
        assertThat("Result does not have any entity", daoCollection.size(), is(greaterThan(0)));
        for (CoworkerDAO c : daos) {
            assertThat("First name was not " + firstName, firstName, is(c.getFirstName()));
            assertThat("Last name was not " + lastName, lastName, is(c.getLastName()));
        }
    }

    @Test
    public void testCoworkersByProject() throws Exception {
        // Todo: Test ProjectService first
        //fail("Underlying service not tested yet, thus this test is not implemented");
    }

    @Test
    public void testCoworkerById() throws Exception {
        final CoworkerDAO coworker = coworkerService.getById(coworkerId);
        assertNotNull("Received coworker was null", coworker);
        assertThat("ID did not match", coworkerId, is(coworker.getCoworkerId()));
    }

    @Test
    public void testCoworkerByTimeFrame() throws Exception {
        // ToDo: Test TimeFrameService first
        //fail("Underlying service not tested yet, thus this test is not implemented");
    }

    @Test
    public void testAddNewCoworker() throws Exception {
        CoworkerDAO coworker = new CoworkerDAO();
        final String testFirstName = "Test first name";
        coworker.setFirstName(testFirstName);
        final String testLastName = "Test last name";
        coworker.setLastName(testLastName);
        final CoworkerDAO addedDao = coworkerService.addNew(coworker);

        assertThat("Expected first name to match", coworker.getFirstName(), is(addedDao.getFirstName()));
        assertThat("Expected last name to match", coworker.getLastName(), is(addedDao.getLastName()));
        assertThat("Expected a date which is not null", addedDao.getCreationDate(), is(not(nullValue())));
        assertThat("Expected a date which is not null", addedDao.getLastUpdatedDate(), is(not(nullValue())));
        assertThat("Expected creation and updated Date to be the same", addedDao.getCreationDate(), is(addedDao.getLastUpdatedDate()));
        assertThat("Expected an Id which is not null", addedDao.getCoworkerId(), is(notNullValue()));
    }

    // FAILS b/C: http://stackoverflow.com/questions/17121620/spring-data-jpa-update-query-not-updating
    // See comment of Oliver Gierke who actually codes this
    @Test
    public void testUpdateCoworker() throws Exception {
        final CoworkerDAO dao = coworkerService.getById(coworkerId);
        final Date lastUpdatedDate = dao.getLastUpdatedDate();
        final String updatedFirstname = "Updated name";
        dao.setFirstName(updatedFirstname);
        final CoworkerDAO updatedDao = coworkerService.update(dao);
        assertNotNull("Expected coworker not being null after update", updatedDao);
        assertThat("Expected first name to be updated", updatedFirstname, is(updatedDao.getFirstName()));
        // FINAL NOTE: this updates only on flush / clear of the entityManager, so dont expect to get back the correct one...
        // You can query for the real object form the DB tho
        // Clearly need to buy the EJB book I had at work again, to look this up
        final CoworkerDAO queriedDao = coworkerService.getById(coworkerId);
        assertThat("Expected last updated date to have changed and be later than the old one", lastUpdatedDate, is(lessThan(queriedDao.getLastUpdatedDate())));
    }

    @Test
    public void testDeleteCoworker() throws Exception {
        CoworkerDAO toDelete = coworkerService.getById(idOfCoworkerToDelete);
        coworkerService.delete(toDelete);
        assertNull("Expected to not receive any coworker", coworkerService.getById(idOfCoworkerToDelete));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCoworkerFailsWitChangedId() throws Exception {
        final CoworkerDAO coworkerDAO = coworkerService.getById(coworkerId);
        assertNotNull("Received Coworker was null", coworkerDAO);
        coworkerDAO.setCoworkerId(coworkerDAO.getCoworkerId() + 2L);
        coworkerService.update(coworkerDAO);
    }
}
