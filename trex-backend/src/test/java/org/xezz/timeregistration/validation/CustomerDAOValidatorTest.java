package org.xezz.timeregistration.validation;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.xezz.timeregistration.dao.CustomerDAO;

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * User: Xezz
 * Date: 08.07.13
 * Time: 10:09
 */
public class CustomerDAOValidatorTest {
    private final CustomerDAOValidator validator = new CustomerDAOValidator();
    private final String customerInfo = "Valid info";
    private final String customerName = "Valid name";
    private final long customerId = 1L;
    private final Date creationDate = new Date();

    @Test
    public void testSupports() throws Exception {
        assertThat("Does not support CustomerDAO class", validator.supports(CustomerDAO.class), is(true));
    }

    @Test
    public void testSupportsNotOtherClass() throws Exception {
        assertThat("Does support a class it is not supposed to support", validator.supports(CustomerDAOValidator.class), is(false));
    }

    @Test
    public void testValidate() throws Exception {
        CustomerDAO customer = new CustomerDAO();
        customer.setName(customerName);
        customer.setCustomerInfo(customerInfo);
        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);
        assertThat("There were errors with a valid customer", false, is(errors.hasErrors()));
    }

    @Test
    public void testValidateWithIdAndDates() throws Exception {
        CustomerDAO customer = new CustomerDAO();
        customer.setName(customerName);
        customer.setCustomerInfo(customerInfo);
        customer.setCustomerId(customerId);
        customer.setCreationDate(creationDate);
        customer.setLastUpdatedDate(creationDate);
        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);
        assertThat("There were errors with a valid customer", false, is(errors.hasErrors()));
    }

    @Test
    public void testValidateFailsWithEmptyName() throws Exception {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.setName("");
        customerDAO.setCustomerInfo(customerInfo);
        Errors errors = new BeanPropertyBindingResult(customerDAO, "customerDAO");
        validator.validate(customerDAO, errors);
        assertThat("There were no errors found in an invalid customer", errors.hasErrors(), is(true));
        assertThat("There was more than one error found", errors.getErrorCount(), is(lessThanOrEqualTo(1)));
        assertThat("Error count was not exactly 1", errors.getErrorCount(), is(equalTo(1)));
        assertThat("Error was not a field error", errors.hasFieldErrors(), is(true));
    }

    @Test
    public void testValidateFailsWithNullName() throws Exception {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.setCustomerInfo(customerInfo);
        Errors errors = new BeanPropertyBindingResult(customerDAO, "customerDAO");
        validator.validate(customerDAO, errors);
        assertThat("There were no errors found in an invalid customer", errors.hasErrors(), is(true));
        assertThat("Error count was not 1", errors.getErrorCount(), is(equalTo(1)));
        assertThat("Error was not a field error", errors.hasFieldErrors(), is(true));
    }

    @Test
    public void testValidateFailsWithEmptyInfo() throws Exception {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.setName(customerName);
        customerDAO.setCustomerInfo("");
        Errors errors = new BeanPropertyBindingResult(customerDAO, "customerDAO");
        validator.validate(customerDAO, errors);
        assertThat("There were no errors found in an invalid customer", errors.hasErrors(), is(true));
        assertThat("Error count was not 1", errors.getErrorCount(), is(equalTo(1)));
        assertThat("Error was no a field error", errors.hasFieldErrors(), is(true));
    }

    @Test
    public void testValidateFailsWithNullInfo() throws Exception {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.setName(customerName);
        customerDAO.setCustomerInfo(null);
        Errors errors = new BeanPropertyBindingResult(customerDAO, "customerDAO");
        validator.validate(customerDAO, errors);
        assertThat("There were no errors found in an invalid customer", errors.hasErrors(), is(true));
        assertThat("Error count was not 1", errors.getErrorCount(), is(equalTo(1)));
        assertThat("Error was no a field error", errors.hasFieldErrors(), is(true));
    }

    @Test
    public void testValidateCustomerIdError() throws Exception {
        CustomerDAO customer = new CustomerDAO();
        customer.setName(customerName);
        customer.setCustomerInfo(customerInfo);
        customer.setCreationDate(creationDate);
        customer.setLastUpdatedDate(creationDate);
        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);
        assertThat("Validation did not have Errors without CustomerId but with dates", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateCreationDateError() throws Exception {
        CustomerDAO customer = new CustomerDAO();
        customer.setName(customerName);
        customer.setCustomerInfo(customerInfo);
        customer.setCustomerId(customerId);
        customer.setLastUpdatedDate(creationDate);
        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);
        assertThat("Validation did not have Errors without CreationDate but with rest", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateLastUpdatedDateError() throws Exception {
        CustomerDAO customer = new CustomerDAO();
        customer.setName(customerName);
        customer.setCustomerInfo(customerInfo);
        customer.setCustomerId(customerId);
        customer.setCreationDate(creationDate);
        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);
        assertThat("Validation did not have Errors without LastUpdateDate but with rest", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateWithLastUpdatedDateError() throws Exception {
        CustomerDAO customer = new CustomerDAO();
        customer.setName(customerName);
        customer.setCustomerInfo(customerInfo);
        customer.setLastUpdatedDate(creationDate);
        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);
        assertThat("Validation did not have Errors with just LastUpdateDate", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateWithCreationDateError() throws Exception {
        CustomerDAO customer = new CustomerDAO();
        customer.setName(customerName);
        customer.setCustomerInfo(customerInfo);
        customer.setCreationDate(creationDate);
        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);
        assertThat("Validation did not have Errors with just CreationDate", errors.hasErrors(), is(equalTo(true)));
    }

    @Test
    public void testValidateWithCustomerIdError() throws Exception {
        CustomerDAO customer = new CustomerDAO();
        customer.setName(customerName);
        customer.setCustomerInfo(customerInfo);
        customer.setCustomerId(customerId);
        Errors errors = new BeanPropertyBindingResult(customer, "customer");
        validator.validate(customer, errors);
        assertThat("Validation did not have Errors with just CustomerId", errors.hasErrors(), is(equalTo(true)));
    }
}
