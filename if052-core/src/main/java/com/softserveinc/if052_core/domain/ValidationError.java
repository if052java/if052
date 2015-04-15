package com.softserveinc.if052_core.domain;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by valentyn on 4/4/15.
 */
public class ValidationError {

    private int responceStatus = 200;

    protected List<Field> fieldErrors;

    public ValidationError() {
        fieldErrors= new ArrayList<Field>();

    }

    public ValidationError(int response, List<Field> fieldErrors) {
        this.responceStatus = response;
        this.fieldErrors = fieldErrors;
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

    public int getResponceStatus() {
        return responceStatus;
    }

    public void setResponceStatus(int responceStatus) {
        this.responceStatus = responceStatus;
    }
}
