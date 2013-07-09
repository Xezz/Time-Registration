package org.xezz.timeregistration.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.xezz.timeregistration.dao.TimeSpanDAO;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 10:56
 * Validate a given TimeSpan is valid
 */
@Component("timeSpanValidator")
public class TimeSpanDAOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return TimeSpanDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "projectId", "field.required", "Must be associated with a Project");
        ValidationUtils.rejectIfEmpty(errors, "coworkerId", "field.required", "Must be associated with a Coworker");
        ValidationUtils.rejectIfEmpty(errors, "startTime", "field.required", "StartTime must not be empty or null");
        ValidationUtils.rejectIfEmpty(errors, "durationInMinutes", "field.required", "Duration must not be empty or null");
        final TimeSpanDAO dao = (TimeSpanDAO) o;
        if (dao.getDurationInMinutes() != null && dao.getDurationInMinutes() <= 0) {
            errors.rejectValue("durationInMinutes", "field.required", "Duration must be greater than 0");
        }
        if ((dao.getTimeSpanId() != null || dao.getCreationDate() != null || dao.getLastUpdatedDate() != null)) {
            if (dao.getTimeSpanId() == null) {
                errors.rejectValue("timeSpanId", "field.required", "Meta data error: timeSpanId was not set");
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
