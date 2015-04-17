package com.softserveinc.if052_restful.controller;

import com.softserveinc.if052_core.domain.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ErrorController {

    /**
     * Handle validation errors
     *
     * @param exception Object that contain description for this exception
     * @return ValidationError Object for response about error
     */
    @ExceptionHandler( MethodArgumentNotValidException.class )
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public ValidationError processValidationError(
        MethodArgumentNotValidException exception
    ) {
        return this.processFieldErrors(
            exception.getBindingResult().getFieldErrors()
        );

    }

    /**
     * Helper for filling field's errors
     *
     * @param fieldErrors List of fields with errors
     * @return ValidationError Object for response about error
     */
    private ValidationError processFieldErrors(
        List<FieldError> fieldErrors
    ) {
        //- Result -//
        ValidationError validationError = new ValidationError();

        for ( FieldError fieldError: fieldErrors ) {
            //- Add field's errors -//
            validationError.addFieldError(
                fieldError.getField(),
                fieldError.getDefaultMessage()
            );
        }

        return validationError;
    }

}