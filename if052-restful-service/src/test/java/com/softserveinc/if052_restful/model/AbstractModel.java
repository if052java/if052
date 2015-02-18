package com.softserveinc.if052_restful.model;

import org.junit.BeforeClass;

import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.Path;

/**
 * Abstract base class for testing of validation
 *
 * @version 1.0
 */
public class AbstractModel {

    protected static Validator validator;


    @BeforeClass
    public static void setUp() {
        // Set validator
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    //- SECTION :: HELPERS -//
    /**
     * Helper for get property name
     *
     * @param path Path with nodes
     * @return String name of property
     */
    protected String getPropertyName( Path path ) {
        // Set default result
        String propertyName = null;

        for ( Path.Node node : path ) {
            String nodeName = node.getName();

            if ( !nodeName.isEmpty() ) {
                propertyName = nodeName;
            }
        }

        return propertyName;
    }
}
