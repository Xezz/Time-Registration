package org.xezz.timeregistration.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xezz.timeregistration.AbstractBaseITConfiguration;
import org.xezz.timeregistration.dao.CustomerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.service.CustomerService;
import org.xezz.timeregistration.service.ProjectService;
import org.xezz.timeregistration.service.TimeSpanService;

import java.util.Iterator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * User: Xezz
 * Date: 03.07.13
 * Time: 23:46
 */
public class ITProjectServiceImplTest extends AbstractBaseITConfiguration {
    private final String existsName = "Timeregistration";
    private final String existsDescription = "Irgendetwas, was der Werner macht!";
    private final Long existsProjectId = 451L;
    private final Long existsCustomerId = 52L;
    private final Long existsTimeSpanId = 501L;

    @Autowired
    ProjectService projectService;
    @Autowired
    CustomerService customerService;
    @Autowired
    TimeSpanService timeSpanService;

    @Test
    public void testGetAll() throws Exception {
        final Iterable<ProjectDAO> allDaos = projectService.getAll();
        assertThat("Received project list was null", allDaos, is(notNullValue()));
        int count = 0;
        final Iterator<ProjectDAO> iterator = allDaos.iterator();
        for (; iterator.hasNext(); iterator.next()) {
            count++;
            if (count > 0) break;
        }
        assertThat("There were no projects", 0, is(lessThan(count)));
    }

    @Test
    public void testGetByName() throws Exception {
        final Iterable<ProjectDAO> byName = projectService.getByName(existsName);
        int count = 0;
        for (ProjectDAO p : byName) {
            assertThat("Name did match", existsName, is(equalTo(p.getName())));
            count++;
        }
        assertThat("There was no project found with that name", count, is(greaterThan(0)));
    }

    @Test
    public void testGetByCustomer() throws Exception {
        final CustomerDAO cust = customerService.getById(existsCustomerId);
        assertThat("Existing customer was null", cust, is(notNullValue()));
        final Iterable<ProjectDAO> custList = projectService.getByCustomer(cust);
        assertThat("Projects from customer was null", custList, is(notNullValue()));
        final Iterator<ProjectDAO> iterator = custList.iterator();
        int count = 0;
        for (;iterator.hasNext(); iterator.next()) {
            count++;
        }
        assertThat("No projects found", 0, is(lessThan(count)));
    }

    @Test
    public void testGetByTimeSpan() throws Exception {
        final TimeSpanDAO tsDao = timeSpanService.getById(existsTimeSpanId);
        assertThat("Existing TimeSpan was null", tsDao, is(notNullValue()));
        final ProjectDAO pDao = projectService.getByTimeFrame(tsDao);
        assertThat("Existing project <- timespan was null", pDao, is(notNullValue()));
        assertThat("Project ID did not match", existsProjectId, is(equalTo(pDao.getProjectId())));
    }

    @Test
    public void testGetById() throws Exception {
        final ProjectDAO pDao = projectService.getById(existsProjectId);
        assertThat("Existing Project was null", pDao, is(notNullValue()));
        assertThat("Project ID's did not match", existsProjectId, is(equalTo(pDao.getProjectId())));
    }

    @Test
    public void testAddNew() throws Exception {
        ProjectDAO createdDao = new ProjectDAO();
        final String projectName = "New Project for Test";
        final String description = "New Description for Test";
        createdDao.setName(projectName);
        createdDao.setDescription(description);
        createdDao.setCustomerId(existsProjectId);
        assertThat("Created DAO WAS NULL", createdDao, is(notNullValue()));
        // Does not work yet, because the repository is not injected into the DAO
        //final ProjectDAO createdProject = projectService.addNew(createdDao);
        //assertThat("Created Project was null", createdProject, is(notNullValue()));
    }

    @Test
    public void testUpdate() throws Exception {
        final ProjectDAO toUpdate = projectService.getById(existsProjectId);
        final String name = toUpdate.getName() + toUpdate.getName();
        assertThat("New name matched old name", name, is(not(equalToIgnoringCase(toUpdate.getName()))));
        toUpdate.setName(name);
        final ProjectDAO updated = projectService.update(toUpdate);
        assertThat("Updated did not get saved", updated, is(notNullValue()));
        assertThat("Updated did not have the new name", name, is(equalTo(updated.getName())));

    }

    @Test
    public void testDelete() throws Exception {
        final ProjectDAO toDelete = projectService.getById(existsProjectId);
        assertThat("Dao to delete did not exist", toDelete, is(notNullValue()));
        projectService.delete(toDelete);
        final ProjectDAO deletedDao = projectService.getById(existsProjectId);
        assertThat("Dao was still in the repository", deletedDao, is(nullValue()));
    }
}
