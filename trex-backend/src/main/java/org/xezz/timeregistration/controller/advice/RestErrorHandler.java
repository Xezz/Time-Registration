package org.xezz.timeregistration.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xezz.timeregistration.dao.validation.ValidationErrorDAO;

import java.util.List;

/**
 * User: Xezz
 * Date: 09.07.13
 * Time: 12:33
 * Handle validation Errors from RESTful controllers
 */
@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDAO processValidationError(MethodArgumentNotValidException ex) {
        final BindingResult bindingResult = ex.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorDAO processFieldErrors(final List<FieldError> errors) {
        final ValidationErrorDAO validationResult = new ValidationErrorDAO();
        for (FieldError fe : errors) {
            validationResult.addFieldError(fe.getField(), fe.getDefaultMessage());
        }
        return validationResult;
    }

}
