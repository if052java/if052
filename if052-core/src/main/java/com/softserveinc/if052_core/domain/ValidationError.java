package com.softserveinc.if052_core.domain;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by valentyn on 4/4/15.
 */
public class ValidationError {

    protected List<Field> fieldErrors;

    public ValidationError() {
        this.fieldErrors = new ArrayList<Field>();
    }

    public void addFieldError( String field, String message ) {
        fieldErrors.add(
            new Field(
                field,
                message
            )
        );
    }

    public List < Field > getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors( List<Field> fieldErrors ) {
        this.fieldErrors = fieldErrors;
    }
}