package org.xezz.timeregistration.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: Xezz
 * Date: 10.06.13
 * Time: 23:11
 */
public class CoworkerTest {
    private Coworker testee;
    private final Long coworkerId = 456787654L;
    private final String firstName = "Test firstName";
    private final String lastName = "Test lastName";
    private final Date creationDate = new Date();
    private final Date lastUpdatedDate = new Date();

    @Before
    public void setUp() throws Exception {
        testee = new Coworker();
        testee.setCoworkerId(coworkerId);
        testee.setFirstName(firstName);
        testee.setLastName(lastName);
        testee.setCreationDate(creationDate);
        testee.setLastUpdatedDate(lastUpdatedDate);
    }

    @Test
    public void testGetCoworkerId() throws Exception {
        assertEquals("Expected coworkerId does not match", coworkerId, testee.getCoworkerId());
    }

    @Test
    public void testSetCoworkerIdWithFreshCoworker() throws Exception {
        final Coworker fresh = new Coworker();
        final Long assertTo = coworkerId + 42314112;
        fresh.setCoworkerId(assertTo);
        assertEquals("CoworkerID is equal", assertTo, fresh.getCoworkerId());
    }

    @Test
    public void testGetFirstName() throws Exception {
        assertEquals("Expected firstName is not equal to the returned String", firstName, testee.getFirstName());
    }

    @Test
    public void testSetFirstName() throws Exception {
        final String assertTo = firstName + "assert fails";
        testee.setFirstName(assertTo);
        assertEquals("New firstName does not equal set firstName", assertTo, testee.getFirstName());

    }

    @Test
    public void testGetLastName() throws Exception {
        assertEquals("getLastName returned not the same value", lastName, testee.getLastName());
    }

    @Test
    public void testSetLastName() throws Exception {
        final String assertTo = lastName + "assert fails";
        testee.setLastName(assertTo);
        assertEquals("Value set by setLastName is not returned", assertTo, testee.getLastName());
    }

    @Test
    public void testGetCreationDate() throws Exception {
        assertEquals("getCreationDate returned not the same Date", creationDate, testee.getCreationDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCreationDate() throws Exception {
        final Date assertTo = new Date(creationDate.getTime() + 34131);
        testee.setCreationDate(assertTo);
    }

    @Test
    public void testGetLastUpdatedDate() throws Exception {
        assertEquals("getLastUpdatedDate returned not the expected Date", lastUpdatedDate, testee.getLastUpdatedDate());
    }

    @Test
    public void testSetLastUpdatedDate() throws Exception {
        final Date assertTo = new Date(lastUpdatedDate.getTime() + 67532);
        testee.setLastUpdatedDate(assertTo);
        assertEquals("Date set by setLastUpdatedDate is not returned", assertTo, testee.getLastUpdatedDate());
    }

    @Test
    public void testEquals() throws Exception {
        Coworker assertTo = testee;
        assertTrue("Equals on same reference not equal", testee.equals(assertTo) && assertTo.equals(testee));
        assertTo = new Coworker();
        assertTo.setFirstName(firstName);
        assertTo.setLastName(lastName);
        assertTo.setCoworkerId(coworkerId);
        assertTo.setCreationDate(creationDate);
        assertTo.setLastUpdatedDate(lastUpdatedDate);
        assertTrue("Two Coworkers that should be equal are not equal", testee.equals(assertTo) && assertTo.equals(testee));
    }

    @Test
    public void testHashCode() throws Exception {
        Coworker assertTo = testee;
        assertEquals("Hashcode is not same for same reference", assertTo.hashCode(), testee.hashCode());
        assertTo = new Coworker();
        assertTo.setFirstName(firstName);
        assertTo.setLastName(lastName);
        assertTo.setCoworkerId(coworkerId);
        assertTo.setCreationDate(creationDate);
        assertTo.setLastUpdatedDate(lastUpdatedDate);
        assertEquals("Hashcode is not same for same reference", assertTo.hashCode(), testee.hashCode());
    }
}
