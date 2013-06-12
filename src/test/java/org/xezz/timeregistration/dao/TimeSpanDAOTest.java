package org.xezz.timeregistration.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.repository.CoworkerRepository;
import org.xezz.timeregistration.repository.ProjectRepository;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * User: bkoch
 * Date: 09.06.13
 * Time: 17:15
 */
public class TimeSpanDAOTest {
    private final Long timeSpanId = 12345678L;
    private final Long projectId = 234567876L;
    private final Long coworkerId = 9876545678L;
    private final Date startTime = new Date();
    private final Long durationInMinutes = 345L;
    private final Date creationDate = new Date();
    private final Date lastUpdatedDate = new Date();
    private TimeSpanDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new TimeSpanDAO(timeSpanId, projectId, coworkerId, startTime, durationInMinutes, creationDate, lastUpdatedDate);
        ProjectRepository projectRepository = Mockito.mock(ProjectRepository.class);
        CoworkerRepository coworkerRepository = Mockito.mock(CoworkerRepository.class);
        Project project = Mockito.mock(Project.class);
        Coworker coworker = Mockito.mock(Coworker.class);
        when(projectRepository.findOne(projectId)).thenReturn(project);
        when(coworkerRepository.findOne(coworkerId)).thenReturn(coworker);
        dao.coworkerRepository = coworkerRepository;
        dao.projectRepository = projectRepository;
    }

    @Test
    public void testGetTimeSpanId() throws Exception {
        assertEquals("Test get timespan ID", timeSpanId, dao.getTimeSpanId());
    }

    @Test
    public void testSetTimeSpanId() throws Exception {
        final Long assertId = timeSpanId + 43431L;
        dao.setTimeSpanId(assertId);
        assertNotEquals("Test set timespan id old value", timeSpanId, dao.getTimeSpanId());
        assertEquals("Test set timespan id new value", assertId, dao.getTimeSpanId());
    }

    @Test
    public void testGetProjectId() throws Exception {
        assertEquals("Test get projectID", projectId, dao.getProjectId());
    }

    @Test
    public void testSetProjectId() throws Exception {
        final Long assertID = projectId + 3242L;
        dao.setProjectId(assertID);
        assertNotEquals("Test projectID old value", projectId, dao.getProjectId());
        assertEquals("Test projectID new value", assertID, dao.getProjectId());
    }

    @Test
    public void testGetCoworkerId() throws Exception {
        assertEquals("Test get coworkerID", coworkerId, dao.getCoworkerId());
    }

    @Test
    public void testSetCoworkerId() throws Exception {
        final Long assertID = coworkerId + 432234L;
        dao.setCoworkerId(assertID);
        assertNotEquals("Test coworkerId old value", coworkerId, dao.getCoworkerId());
        assertEquals("Test coworkerId new value", assertID, dao.getCoworkerId());
    }

    @Test
    public void testGetStartTime() throws Exception {
        assertEquals("Test start time", startTime, dao.getStartTime());
    }

    @Test
    public void testSetStartTime() throws Exception {
        final Date assertTime = new Date(startTime.getTime() + 123456);
        dao.setStartTime(assertTime);
        assertNotEquals("Test set start time old value", startTime, dao.getStartTime());
        assertEquals("Test set start time new value", assertTime, dao.getStartTime());
    }

    @Test
    public void testGetDurationInMinutes() throws Exception {
        assertEquals("Test duration", durationInMinutes, dao.getDurationInMinutes());
    }

    @Test
    public void testSetDurationInMinutes() throws Exception {
        final Long assertDuration = durationInMinutes * 2;
        dao.setDurationInMinutes(assertDuration);
        assertNotEquals("Test set duration old value", durationInMinutes, dao.getDurationInMinutes());
        assertEquals("Test set duration new value", assertDuration, dao.getDurationInMinutes());
    }

    @Test
    public void testGetCreationDate() throws Exception {
        assertEquals("Test creation date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testSetCreationDate() throws Exception {
        final Date assertDate = new Date(creationDate.getTime() + 422423);
        dao.setCreationDate(assertDate);
        assertNotEquals("Test set creation old value", creationDate, dao.getCreationDate());
        assertEquals("Test set creation new value", assertDate, dao.getCreationDate());
    }

    @Test
    public void testGetLastUpdatedDate() throws Exception {
        assertEquals("Test get updated date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testSetLastUpdatedDate() throws Exception {
        final Date assertDate = new Date(lastUpdatedDate.getTime() + 95898943);
        dao.setLastUpdatedDate(assertDate);
        assertNotEquals("Test last update date old value", lastUpdatedDate, dao.getLastUpdatedDate());
        assertEquals("Test last update date new value", assertDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testEquals() throws Exception {
        TimeSpanDAO assertDao = dao;
        assertTrue("Reference equals", assertDao.equals(dao) && dao.equals(assertDao));
        assertDao = new TimeSpanDAO(timeSpanId, projectId, coworkerId, startTime, durationInMinutes, creationDate, lastUpdatedDate);
        assertTrue("Constructor equals", assertDao.equals(dao) && dao.equals(assertDao));
    }

    @Test
    public void testHashCode() throws Exception {
        TimeSpanDAO assertDao = dao;
        assertEquals("Reference hashcode", dao.hashCode(), assertDao.hashCode());
        assertDao = new TimeSpanDAO(timeSpanId, projectId, coworkerId, startTime, durationInMinutes, creationDate, lastUpdatedDate);
        assertEquals("Constructor hashcode", dao.hashCode(), assertDao.hashCode());
    }

    @Test
    public void testReceiveProject() throws Exception {
        assertNotNull("Test receive project", dao.receiveProject());
    }

    @Test
    public void testReceiveCoworker() throws Exception {
        assertNotNull("Test receive coworker", dao.receiveCoworker());
    }

    // TODO: MORE tests, especially assuming fails!
}
