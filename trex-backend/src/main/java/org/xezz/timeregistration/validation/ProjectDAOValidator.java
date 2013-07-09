package org.xezz.timeregistration.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.xezz.timeregistration.dao.ProjectDAO;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 09:09
 * Validate a given Project is valid
 */
@Component("projectValidator")
public class ProjectDAOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProjectDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Name must not be empty or null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "Description must not be empty or null");
        ValidationUtils.rejectIfEmpty(errors, "customerId", "field.required", "Must be associated with a Customer");
        final ProjectDAO dao = (ProjectDAO) o;
        if ((dao.getProjectId() != null || dao.getCreationDate() != null || dao.getLastUpdatedDate() != null)) {
            if (dao.getProjectId() == null) {
                errors.rejectValue("projectId", "field.required", "Meta data error: projectId was not set");
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
