package org.xezz.timeregistration.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.xezz.timeregistration.dao.TimeSpanDAO;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 10:56
 */
public class TimeSpanDAOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return TimeSpanDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "projectId", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "coworkerId", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "startTime", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "durationInMinutes", "field.required");
        final TimeSpanDAO dao = (TimeSpanDAO) o;
        if ((dao.getTimeSpanId() != null || dao.getCreationDate() != null || dao.getLastUpdatedDate() != null) &&
                (dao.getTimeSpanId() == null || dao.getCreationDate() == null || dao.getLastUpdatedDate() == null)) {
            errors.reject("Meta data", "Incomplete meta data");
        }
    }
}
