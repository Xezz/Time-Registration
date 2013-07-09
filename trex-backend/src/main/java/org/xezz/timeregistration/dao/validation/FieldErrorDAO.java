package org.xezz.timeregistration.dao.validation;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 12:36
 */
public class FieldErrorDAO {

    private String field;
    private String message;

    public FieldErrorDAO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
