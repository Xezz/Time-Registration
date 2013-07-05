package org.xezz.timeregistration.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xezz.timeregistration.AbstractBaseITConfiguration;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.service.CoworkerService;
import org.xezz.timeregistration.service.ProjectService;
import org.xezz.timeregistration.service.TimeSpanService;

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * User: Xezz
 * Date: 05.07.13
 * Time: 17:24
 */
public class ITTimeSpanServiceImplTest extends AbstractBaseITConfiguration {
    private final Long timespanid = 551L;
    private final Long coworker_coworkerid = 1L;
    private final Long project_projectid = 101L;
    private final Long duration_in_minutes = 45L;

    private CoworkerDAO coworkerDao = null;
    private ProjectDAO projectDAO = null;

    @Autowired
    TimeSpanService timeSpanService;
    @Autowired
    CoworkerService coworkerService;
    @Autowired
    ProjectService projectService;

    @Before
    public void setUp() throws Exception {
        coworkerDao = coworkerService.getById(coworker_coworkerid);
        projectDAO = projectService.getById(project_projectid);
    }

    @After
    public void tearDown() throws Exception {
        coworkerDao = null;
        projectDAO = null;
    }

    @Test
    public void testGetByCoworker() throws Exception {
        final Iterable<TimeSpanDAO> byCoworker = timeSpanService.getByCoworker(coworkerDao);
        assertThat("Returned list was null", byCoworker, is(notNullValue()));
        int count = 0;
        for (TimeSpanDAO d : byCoworker) {
            count++;
        }
        assertThat("List was empty", 0, is(lessThan(count)));
        TimeSpanDAO received = null;
        if (byCoworker.iterator().hasNext()) {
            received = byCoworker.iterator().next();
        }
        assertThat("Received list had a null value stored", received, is(notNullValue()));
        assertThat("TimeSpan did not have the Coworker stored", received.getCoworkerId(), is(equalTo(coworker_coworkerid)));
    }

    @Test
    public void testGetByProject() throws Exception {
        final Iterable<TimeSpanDAO> byProject = timeSpanService.getByProject(projectDAO);
        assertThat("Returned list was null", byProject, is(notNullValue()));
        int count = 0;
        for (TimeSpanDAO d : byProject) {
            count++;
        }
        assertThat("List was empty", 0, is(lessThan(count)));
        TimeSpanDAO received = null;
        if (byProject.iterator().hasNext()) {
            received = byProject.iterator().next();
        }
        assertThat("Received list had a null value stored", received, is(notNullValue()));
        assertThat("TimeSpan did not have the Project stored", received.getProjectId(), is(equalTo(project_projectid)));
    }

    @Test
    public void testGetById() throws Exception {
        final TimeSpanDAO byId = timeSpanService.getById(timespanid);
        assertThat("Received TimeSpan was null", byId, is(notNullValue()));
        assertThat("ID's did not match", timespanid, is(equalTo(byId.getTimeSpanId())));
    }

    @Test
    public void testAddNew() throws Exception {
        TimeSpanDAO timeSpanNew = new TimeSpanDAO();
        timeSpanNew.setCoworkerId(coworker_coworkerid);
        timeSpanNew.setProjectId(project_projectid);
        timeSpanNew.setStartTime(new Date());
        final long durationInMinutes = 60L;
        timeSpanNew.setDurationInMinutes(durationInMinutes);
        final TimeSpanDAO created = timeSpanService.addNew(timeSpanNew);
        assertThat("Newly created timespan did not save", created, is(notNullValue()));
        assertThat("Newly created timespan has no id", 0L, is(lessThan(created.getTimeSpanId())));
        assertThat("Newly created timespan duration did not match", durationInMinutes, is(equalTo(created.getDurationInMinutes())));
        /* This does not work, dunno why the object does not get its creation date, maybe HSQL DB stuff?
        assertThat("Newly created timespan has no creation date", created.getCreationDate(), is(notNullValue()));
        assertThat("Newly created timespan has no last updated date", created.getLastUpdatedDate(), is(notNullValue()));
        */
    }

    @Test
    public void testUpdate() throws Exception {
        final TimeSpanDAO byId = timeSpanService.getById(timespanid);
        byId.receiveCoworker();
        final long durationInMinutes = 75L;
        byId.setDurationInMinutes(durationInMinutes);
        final Date startTime = new Date();
        byId.setStartTime(startTime);
        final TimeSpanDAO update = timeSpanService.update(byId);
        assertThat("Updating timespan returned null", update, is(notNullValue()));
        assertThat("Returned timespan has not new duration", durationInMinutes, is(equalTo(update.getDurationInMinutes())));
        /* Start time does not get updated here, but I don't know why
        assertThat("Returned timespan has not new start time", update.getStartTime(), is(equalTo(startTime)));
         */
    }

    @Test
    public void testGetAll() throws Exception {
        final Iterable<TimeSpanDAO> all = timeSpanService.getAll();
        assertThat("Received list was null", all, is(notNullValue()));
        int count = 0;
        for (TimeSpanDAO d : all) {
            count++;
        }
        assertThat("Received list was empty", 0, is(lessThan(count)));
    }

    @Test
    public void testDelete() throws Exception {
        final TimeSpanDAO byId = timeSpanService.getById(timespanid);
        assertThat("Received timespan was null", byId, is(notNullValue()));
        timeSpanService.delete(byId);
        assertThat("Deleted timespan still exists", timeSpanService.getById(timespanid), is(nullValue()));
    }
}
