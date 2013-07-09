package org.xezz.timeregistration.validation;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.model.Coworker;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 11:15
 */
public class CoworkerDAOValidatorTest {
    final CoworkerDAOValidator validator = new CoworkerDAOValidator();
    private final String firstName = "Valid first name";
    private final String lastName = "Valid last name";
    private final long coworkerId = 1L;
    private final Date date = new Date();

    @Test
    public void testSupports() throws Exception {
        assertThat("Validator does not support expected Class", validator.supports(CoworkerDAO.class), is(true));
    }

    @Test
    public void testSupportsFailsWithOtherClass() throws Exception {
        assertThat("Validator supports unsupported Class", validator.supports(Coworker.class), is(false));
    }

    @Test
    public void testValidateShortDAO() throws Exception {
        CoworkerDAO dao = new CoworkerDAO();
        dao.setFirstName(firstName);
        dao.setLastName(lastName);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dao, "coworker");
        validator.validate(dao, errors);
        assertThat("Validation failed on a valid CoworkerDAO", errors.hasErrors(), is(false));
    }

    @Test
    public void testValidate() throws Exception {
        CoworkerDAO dao = new CoworkerDAO();
        dao.setFirstName(firstName);
        dao.setLastName(lastName);
        dao.setCoworkerId(coworkerId);
        dao.setCreationDate(date);
        dao.setLastUpdatedDate(date);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dao, "coworker");
        validator.validate(dao, errors);
        assertThat("Validation failed on a valid CoworkerDAO", errors.hasErrors(), is(false));
    }

    @Test
    public void testValidateMetaCoworkerIdError() throws Exception {
        CoworkerDAO dao = new CoworkerDAO();
        dao.setFirstName(firstName);
        dao.setLastName(lastName);
        dao.setCoworkerId(coworkerId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dao, "coworker");
        validator.validate(dao, errors);
        assertThat("Validation passed with only CoworkerId", errors.hasErrors(), is(true));
    }

    @Test
    public void testValidateMetaCreationDateError() throws Exception {
        CoworkerDAO dao = new CoworkerDAO();
        dao.setFirstName(firstName);
        dao.setLastName(lastName);
        dao.setCreationDate(date);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dao, "coworker");
        validator.validate(dao, errors);
        assertThat("Validation passed with only CreationDate", errors.hasErrors(), is(true));
    }

    @Test
    public void testValidateMetaLastUpdatedDateError() throws Exception {
        CoworkerDAO dao = new CoworkerDAO();
        dao.setFirstName(firstName);
        dao.setLastName(lastName);
        dao.setLastUpdatedDate(date);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dao, "coworker");
        validator.validate(dao, errors);
        assertThat("Validation passed with only LastUpdatedDate", errors.hasErrors(), is(true));
    }

    @Test
    public void testValidateFirstNameError() throws Exception {
        CoworkerDAO dao = new CoworkerDAO();
        dao.setLastName(lastName);
        dao.setCoworkerId(coworkerId);
        dao.setCreationDate(date);
        dao.setLastUpdatedDate(date);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dao, "coworker");
        validator.validate(dao, errors);
        assertThat("Validation passed without firstName error", errors.hasErrors(), is(true));
    }

    @Test
    public void testValidateLastNameError() throws Exception {
        CoworkerDAO dao = new CoworkerDAO();
        dao.setFirstName(firstName);
        dao.setCoworkerId(coworkerId);
        dao.setCreationDate(date);
        dao.setLastUpdatedDate(date);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dao, "coworker");
        validator.validate(dao, errors);
        assertThat("Validation passed without lastName error", errors.hasErrors(), is(true));
    }
}
