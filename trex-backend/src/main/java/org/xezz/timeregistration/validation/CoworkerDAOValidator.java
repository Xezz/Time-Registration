package org.xezz.timeregistration.validation;

import org.springframework.stereotype.Component;
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
@Component("coworkerValidator")
public class CoworkerDAOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CoworkerDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required", "firstName must not be null or empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required", "lastName must not be null or empty");
        final CoworkerDAO dao = (CoworkerDAO) o;
        // If an id, creation date or last updated date exists all others have to exist too
        if ((dao.getCoworkerId() != null || dao.getCreationDate() != null || dao.getLastUpdatedDate() != null)) {
            if (dao.getCoworkerId() == null) {
                errors.rejectValue("coworkerId", "field.required", "Meta data error: coworkerId was not set");
            }
            if (dao.getCreationDate() == null) {
                errors.rejectValue("creationDate", "field.required", "Meta data error: creationDate was not set");
            }
            if (dao.getLastUpdatedDate() == null) {
                errors.rejectValue("lastUpdatedDate", "field.required", "Meta data error: lastUpdatedDate was not set");
            }
            if (dao.getCreationDate() != null &&
                    dao.getLastUpdatedDate() != null &&
                    dao.getLastUpdatedDate().before(dao.getCreationDate())) {
                errors.rejectValue("lastUpdatedDate", "field.required", "Can not be updated before it has been created");
            }
        }
    }
}
