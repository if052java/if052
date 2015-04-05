package com.softserveinc.if052_core.domain;

/**
 * Created by valentyn on 4/4/15.
 */
public class Field {

    protected String field;

    protected String message;

    public Field(
        String field,
        String message
    ) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public void setField( String field ) {
        this.field = field;
    }

    public void setMessage( String message ) {
        this.message = message;
    }
}
