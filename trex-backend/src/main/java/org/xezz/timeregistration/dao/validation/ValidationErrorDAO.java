package org.xezz.timeregistration.dao.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 12:37
 * A collection of all errors that happened during Validation
 */
public class ValidationErrorDAO {
    private List<FieldErrorDAO> errors = new ArrayList<FieldErrorDAO>();

    public ValidationErrorDAO() {
    }

    /**
     * Add a new FieldError
     *
     * @param path    affected field
     * @param message resulting message
     */
    public void addFieldError(String path, String message) {
        final FieldErrorDAO fieldErrorDAO = new FieldErrorDAO(path, message);
        errors.add(fieldErrorDAO);
    }

    /**
     * Get all validation errors
     *
     * @return List of all FieldErrors
     */
    public List<FieldErrorDAO> getErrors() {
        return errors;
    }
}
