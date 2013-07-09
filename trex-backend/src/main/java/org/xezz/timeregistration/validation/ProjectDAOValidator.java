package org.xezz.timeregistration.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.xezz.timeregistration.dao.ProjectDAO;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 09:09
 */
public class ProjectDAOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProjectDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (!(o instanceof ProjectDAO)) {
            errors.reject("Not a Project");
            return;
        }
        ProjectDAO dao = (ProjectDAO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "customerId", "field.required");
        if ((dao.getProjectId() != null || dao.getCreationDate() != null || dao.getLastUpdatedDate() != null) &&
                (dao.getProjectId() == null || dao.getCreationDate() == null || dao.getLastUpdatedDate() == null)) {
            errors.reject("Meta data", "Incomplete meta data");
        }
    }
}
