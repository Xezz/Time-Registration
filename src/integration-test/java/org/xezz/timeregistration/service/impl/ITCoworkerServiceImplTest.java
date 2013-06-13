package org.xezz.timeregistration.service.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.xezz.timeregistration.config.AppTestConfig;
import org.xezz.timeregistration.config.DataTestConfig;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.services.CoworkerService;
import org.xezz.timeregistration.services.impl.CoworkerServiceImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * User: bkoch
 * Date: 08.06.13
 * Time: 13:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppTestConfig.class, DataTestConfig.class, CoworkerServiceImpl.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("integration-test")
@Configurable
public class ITCoworkerServiceImplTest {

    // Required by DBUnit
    @Resource
    DataSource dataSource;

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
        assertNotNull("service.getAll returned null", coworkerService.coworkersAll());

    }

    @Test
    public void testCoworkersByFirstName() throws Exception {

    }

    @Test
    public void testCoworkersByLastName() throws Exception {

    }

    @Test
    public void testCoworkersByFirstAndLastName() throws Exception {

    }

    @Test
    public void testCoworkersByProject() throws Exception {

    }

    @Test
    public void testCoworkerById() throws Exception {

    }

    @Test
    public void testCoworkerByTimeFrame() throws Exception {

    }

    @Test
    public void testAddNewCoworker() throws Exception {
        CoworkerDAO coworker = new CoworkerDAO();
        final String firstName = "Bastian";
        coworker.setFirstName(firstName);
        final String lastName = "Koch";
        coworker.setLastName(lastName);
        coworkerService.addNewCoworker(coworker);
        final Iterable<CoworkerDAO> daoList = coworkerService.coworkersAll();
        int daoListSize = 0;
        CoworkerDAO receivedDao = new CoworkerDAO();
        for (CoworkerDAO cDao : daoList) {
            if (daoListSize == 0) {
                receivedDao = cDao;
            }
            daoListSize++;
        }
        assertThat(daoListSize, is(not(0)));
        assertThat(coworker.getFirstName(), is(receivedDao.getFirstName()));
        assertThat(coworker.getLastName(), is(receivedDao.getLastName()));
        assertThat(receivedDao.getCreationDate(), is(not(nullValue())));
        assertThat(receivedDao.getLastUpdatedDate(), is(not(nullValue())));
        assertThat(receivedDao.getCreationDate(), is(receivedDao.getLastUpdatedDate()));
    }

    @Test
    public void testUpdateCoworker() throws Exception {

    }

    @Test
    public void testDeleteCoworker() throws Exception {

    }
}
