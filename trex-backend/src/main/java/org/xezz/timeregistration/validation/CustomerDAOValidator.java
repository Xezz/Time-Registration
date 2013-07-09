package org.xezz.timeregistration.validation;

import org.springframework.stereotype.Component;
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
@Component("customerValidator")
public class CustomerDAOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Name must not be empty or null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerInfo", "field.required", "CustomerInfo must not be empty or null");
        final CustomerDAO dao = (CustomerDAO) o;
        // If an id, creation date or last updated date exists all others have to exist too
        if ((dao.getCustomerId() != null || dao.getCreationDate() != null || dao.getLastUpdatedDate() != null)) {
            if (dao.getCustomerId() == null) {
                errors.rejectValue("customerId", "field.required", "Meta data error: customerId was not set");
            }
            if (dao.getCreationDate() == null) {
                errors.rejectValue("creationDate", "field.required", "Meta data error: creationDate was not set");
            }
            if (dao.getLastUpdatedDate() == null) {
                errors.rejectValue("lastUpdatedDate", "field.required", "Meta data error: lastUpdatedDate was not set");
            }
        }
    }
}
