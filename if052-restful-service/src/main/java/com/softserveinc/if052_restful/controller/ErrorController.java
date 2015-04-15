package com.softserveinc.if052_restful.controller;

import com.softserveinc.if052_core.domain.Field;
import com.softserveinc.if052_core.domain.ValidationError;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class ErrorController {

    public ErrorController() {

    }

    public List<Field> processValidationError(
        List<FieldError> errors
    ) {
        ValidationError validationError = new ValidationError();

        for ( FieldError fieldError: errors ) {
            //- Add field's errors -//
            validationError.addFieldError(
                fieldError.getField(),
                fieldError.getDefaultMessage()
            );
        }

        return validationError.getFieldErrors();
    }

}
