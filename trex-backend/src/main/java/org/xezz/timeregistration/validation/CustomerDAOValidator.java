package org.xezz.timeregistration.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.xezz.timeregistration.dao.CustomerDAO;

/**
 * User: Xezz
 * Date: 08.07.13
 * Time: 10:01
 * Validate a given Customer is valid
 */
public class CustomerDAOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerDAO customer = (CustomerDAO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerInfo", "field.required");
        // If an id, creation date or last updated date exists all others have to exist too
        if ((customer.getCustomerId() != null || customer.getCreationDate() != null || customer.getLastUpdatedDate() != null) &&
                (customer.getCustomerId() == null || customer.getCreationDate() == null || customer.getLastUpdatedDate() == null)) {
            errors.reject("Meta data", "Incomplete meta data");
        }
    }
}
