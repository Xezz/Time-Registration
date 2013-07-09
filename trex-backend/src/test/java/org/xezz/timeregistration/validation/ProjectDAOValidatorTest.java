package org.xezz.timeregistration.validation;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.model.Project;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 09:10
 */
public class ProjectDAOValidatorTest {
    private final ProjectDAOValidator validator = new ProjectDAOValidator();
    private final String name = "Valid name";
    private final String description = "Valid description";
    private final Date creationDate = new Date();
    private final long customerId = 12345L;
    private final long projectId = 323232L;

    @Test
    public void testSupports() throws Exception {
        assertThat("Does not support expected Class", validator.supports(ProjectDAO.class), is(equalTo(true)));
    }

    @Test
    public void testSupportDoesNotSupportOtherClass() throws Exception {
        assertThat("Supports a Class it is not supposed to support", validator.supports(Project.class), is(equalTo(false)));
    }

    @Test
    public void testValidate() throws Exception {
        ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(description);
        projectDAO.setCreationDate(creationDate);
        projectDAO.setLastUpdatedDate(creationDate);
        projectDAO.setCustomerId(customerId);
        projectDAO.setProjectId(projectId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Unexpected Errors validating a valid object", errors.hasErrors(), is(equalTo(false)));
    }

    @Test
    public void testValidateNewObject() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(description);
        projectDAO.setCustomerId(customerId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Unexpected Errors validating a valid object", errors.hasErrors(), is(equalTo(false)));
    }

    @Test
    public void testValidateEmptyNameError() throws Exception {
        ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName("");
        projectDAO.setDescription(description);
        projectDAO.setCustomerId(customerId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors with empty Name", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateNullNameError() throws Exception {
        ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(null);
        projectDAO.setDescription(description);
        projectDAO.setCustomerId(customerId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors with null Name", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateEmptyDescriptionError() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription("");
        projectDAO.setCustomerId(customerId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors with empty Description", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateNullDescriptionError() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(null);
        projectDAO.setCustomerId(customerId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors with null Description", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateNullCustomerIdError() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(description);
        projectDAO.setCustomerId(null);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors with null CustomerId", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateProjectIdError() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(description);
        projectDAO.setCreationDate(creationDate);
        projectDAO.setCustomerId(customerId);
        projectDAO.setLastUpdatedDate(creationDate);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors without ProjectId but with dates", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateCreationDateError() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(description);
        projectDAO.setCustomerId(customerId);
        projectDAO.setProjectId(projectId);
        projectDAO.setLastUpdatedDate(creationDate);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors without Creationdate but with Id and Last updated Date", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateLastUpdatedDateError() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(description);
        projectDAO.setCreationDate(creationDate);
        projectDAO.setProjectId(projectId);
        projectDAO.setCustomerId(customerId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors without LastUpdatedDate but with Id and Creation Date", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateWithLastUpdatedDateError() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(description);
        projectDAO.setLastUpdatedDate(creationDate);
        projectDAO.setProjectId(projectId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors with just LastUpdatedDate", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateWithCreationDateError() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(description);
        projectDAO.setCreationDate(creationDate);
        projectDAO.setProjectId(projectId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors with just CreationDate", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateWithCustomerIdError() throws Exception {
        final ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.setName(name);
        projectDAO.setDescription(description);
        projectDAO.setProjectId(projectId);
        projectDAO.setCustomerId(customerId);
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(projectDAO, "projectDAO");
        validator.validate(projectDAO, errors);
        assertThat("Validation did not have Errors with just CustomerId", errors.hasErrors(), is(equalTo(true)));
    }
}
