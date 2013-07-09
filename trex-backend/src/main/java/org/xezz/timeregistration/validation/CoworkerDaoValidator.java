package org.xezz.timeregistration.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.xezz.timeregistration.dao.CoworkerDAO;

/**
 * User: Xezz
 * Date: 08.07.13
 * Time: 09:39
 * Validate that a given CoworkerDAO is valid
 */
public class CoworkerDAOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CoworkerDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        final CoworkerDAO coworker = (CoworkerDAO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required");
        // If an id, creation date or last updated date exists all others have to exist too
        if ((coworker.getCoworkerId() != null || coworker.getCreationDate() != null || coworker.getLastUpdatedDate() != null) &&
                (coworker.getCoworkerId() == null || coworker.getCreationDate() == null || coworker.getLastUpdatedDate() == null)) {
            errors.reject("Meta data", "Incomplete meta data");
        }
    }
}
