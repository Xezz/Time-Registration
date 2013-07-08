package org.xezz.timeregistration.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xezz.timeregistration.dao.CustomerDAO;

/**
 * User: Xezz
 * Date: 08.07.13
 * Time: 10:01
 */
public class CustomerDAOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerDAO customer = (CustomerDAO) o;
        if(customer.getName() == null || customer.getName().trim().equals("")) {
            errors.rejectValue("name", "Name empty String");
        }
        if (customer.getCustomerInfo() == null || customer.getCustomerInfo().trim().equals("")) {
            errors.rejectValue("info", "Info empty String");
        }
        // If an id, creation date or last updated date exists all others have to exist too
        if ((customer.getCustomerId() != null || customer.getCreationDate() != null || customer.getLastUpdatedDate() != null) &&
                (customer.getCustomerId() == null || customer.getCreationDate() == null || customer.getLastUpdatedDate() == null)) {
            errors.rejectValue("Meta data", "Incomplete meta data");
        }
    }
}
