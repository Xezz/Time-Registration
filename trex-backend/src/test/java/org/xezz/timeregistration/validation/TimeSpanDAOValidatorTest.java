package org.xezz.timeregistration.validation;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.model.TimeSpan;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 10:56
 */
public class TimeSpanDAOValidatorTest {
    final private TimeSpanDAOValidator validator = new TimeSpanDAOValidator();
    private final long projectId = 1L;
    private final long coworkerId = 2L;
    private final long timeSpanId = 3L;
    private final Date date = new Date();
    private final long duration = 60L;

    @Test
    public void testSupports() throws Exception {
        assertThat("Validator does not support expected Class TimeSpanDAO", validator.supports(TimeSpanDAO.class), is(true));
    }

    @Test
    public void testSupportsNotOtherClass() throws Exception {
        assertThat("Validator does support unsupported Class", validator.supports(TimeSpan.class), is(false));
    }

    @Test
    public void testValidate() throws Exception {
        final TimeSpanDAO timeSpanDAO = new TimeSpanDAO();
        timeSpanDAO.setProjectId(projectId);
        timeSpanDAO.setCoworkerId(coworkerId);
        timeSpanDAO.setTimeSpanId(timeSpanId);
        timeSpanDAO.setStartTime(date);
        timeSpanDAO.setDurationInMinutes(duration);
        timeSpanDAO.setCreationDate(date);
        timeSpanDAO.setLastUpdatedDate(date);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(timeSpanDAO, "timespan");
        validator.validate(timeSpanDAO, errors);
        assertThat("Validator had Errors on a valid TimeSpanDAO", errors.hasErrors(), is(false));
    }

    @Test
    public void testValidateNewDAO() throws Exception {
        final TimeSpanDAO timeSpanDAO = new TimeSpanDAO();
        timeSpanDAO.setProjectId(projectId);
        timeSpanDAO.setCoworkerId(coworkerId);
        timeSpanDAO.setStartTime(date);
        timeSpanDAO.setDurationInMinutes(duration);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(timeSpanDAO, "timespan");
        validator.validate(timeSpanDAO, errors);
        assertThat("Validator had Errors on a valid TimeSpanDAO", errors.hasErrors(), is(false));
    }

    @Test
    public void testValidateProjectIdError() throws Exception {
        final TimeSpanDAO timeSpanDAO = new TimeSpanDAO();
        timeSpanDAO.setCoworkerId(coworkerId);
        timeSpanDAO.setStartTime(date);
        timeSpanDAO.setDurationInMinutes(duration);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(timeSpanDAO, "timespan");
        validator.validate(timeSpanDAO, errors);
        assertThat("Validation passed without projectId", errors.hasErrors(), is(true));
    }

    @Test
    public void testValidateCoworkerIdError() throws Exception {
        final TimeSpanDAO timeSpanDAO = new TimeSpanDAO();
        timeSpanDAO.setProjectId(projectId);
        timeSpanDAO.setStartTime(date);
        timeSpanDAO.setDurationInMinutes(duration);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(timeSpanDAO, "timespan");
        validator.validate(timeSpanDAO, errors);
        assertThat("Validation passed without coworkerId", errors.hasErrors(), is(true));
    }

    @Test
    public void testValidateStartTimeError() throws Exception {
        final TimeSpanDAO timeSpanDAO = new TimeSpanDAO();
        timeSpanDAO.setProjectId(projectId);
        timeSpanDAO.setCoworkerId(coworkerId);
        timeSpanDAO.setDurationInMinutes(duration);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(timeSpanDAO, "timespan");
        validator.validate(timeSpanDAO, errors);
        assertThat("Validation passed without startTime", errors.hasErrors(), is(true));
    }

    @Test
    public void testValidateDurationError() throws Exception {
        final TimeSpanDAO timeSpanDAO = new TimeSpanDAO();
        timeSpanDAO.setProjectId(projectId);
        timeSpanDAO.setCoworkerId(coworkerId);
        timeSpanDAO.setStartTime(date);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(timeSpanDAO, "timespan");
        validator.validate(timeSpanDAO, errors);
        assertThat("Validation passed without duration", errors.hasErrors(), is(true));
    }
}
