package org.xezz.timeregistration.dao;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * User: Xezz
 * Date: 08.06.13
 * Time: 16:01
 */
public class CoworkerDAOTest {
    private final String firstname = "Firstname";
    private final String lastname = "Lastname";
    private final Date creationDate = new Date();
    private final Date lastUpdatedDate = new Date();
    private CoworkerDAO dao;
    private Long coworkerId = 1337666L;

    @Before
    public void setUp() throws Exception {
        dao = new CoworkerDAO(coworkerId, firstname, lastname, creationDate, lastUpdatedDate);
    }

    @Test
    public void testGetCoworkerId() throws Exception {
        assertEquals("Test of ID equality", coworkerId, dao.getCoworkerId());
    }

    @Test
    public void testSetCoworkerId() throws Exception {
        final Long id = 123451232L;
        dao.setCoworkerId(id);
        assertEquals("Test ID equality", id, dao.getCoworkerId());
        assertNotEquals("Test inequality of given ID and preset ID", coworkerId, dao.getCoworkerId());
    }

    @Test
    public void testGetFirstName() throws Exception {
        assertEquals("Test of equal names", firstname, dao.getFirstName());
    }

    @Test
    public void testSetFirstName() throws Exception {
        final String newFirst = "new first name";
        dao.setFirstName(newFirst);
        assertEquals("Test setting new first name equality", newFirst, dao.getFirstName());
        assertNotEquals("Test setting new first name non-equality with old first name", firstname, dao.getFirstName());
    }

    @Test
    public void testGetLastName() throws Exception {
        assertEquals("Test getting last name", lastname, dao.getLastName());
    }

    @Test
    public void testSetLastName() throws Exception {
        final String newLast = "new last name";
        dao.setLastName(newLast);
        assertEquals("Test setting new last name equality", newLast, dao.getLastName());
        assertNotEquals("Test setting new last name non-equality with old last name", lastname, dao.getLastName());
    }

    @Test
    public void testGetCreationDate() throws Exception {
        assertEquals("Test getting creation Date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testSetCreationDate() throws Exception {
        final Date date = new Date(123456L);
        dao.setCreationDate(date);
        assertEquals("Test setting creation date", date, dao.getCreationDate());
        assertNotEquals("Test setting creation date not equal to old value", creationDate, dao.getCreationDate());
    }

    @Test
    public void testGetLastUpdatedDate() throws Exception {
        assertEquals("Test getting creation date", creationDate, dao.getCreationDate());
    }

    @Test
    public void testSetLastUpdatedDate() throws Exception {
        final Date date = new Date(1337090L);
        dao.setLastUpdatedDate(date);
        assertEquals("Test setting creation date", date, dao.getLastUpdatedDate());
        assertNotEquals("Test setting creation date not equal to old date", lastUpdatedDate, dao.getLastUpdatedDate());
    }

    @Test
    public void testEquals() throws Exception {

    }

    @Test
    public void testHashCode() throws Exception {

    }
}
