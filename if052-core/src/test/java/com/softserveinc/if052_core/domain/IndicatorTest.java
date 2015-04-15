package com.softserveinc.if052_core.domain;

import com.softserveinc.if052_core.model.AbstractModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by valentyn on 4/8/15.
 */
public class IndicatorTest extends AbstractModel {

    @Test
    public void testIndicatorValueFailure(){
        Set<ConstraintViolation<Indicator>> constraintViolationSet;

        Indicator indicatorFailureValue = new Indicator(
            new Date(),
            new Double(-5),
            new Integer(-3),
            new WaterMeter()
        );

        constraintViolationSet = validator.validate(indicatorFailureValue);
        assertEquals( 2, constraintViolationSet.size() );

        for( ConstraintViolation<Indicator> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("value");
                    add("tariffPerDate");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class> () {{
                    add(Min.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("must be greater than or equal to 0");
                }}.contains(constraintViolation.getMessage())
            );
        }
    }

    @Test
    public void testIndicatorFailureNotNull(){
        Set<ConstraintViolation<Indicator>> constraintViolationSet;

        Indicator indicatorFailureNotNull = new Indicator(
            null,
            null,
            null,
            null
        );

        constraintViolationSet = validator.validate(indicatorFailureNotNull);
        assertEquals( 4, constraintViolationSet.size() );

        for( ConstraintViolation<Indicator> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("date");
                    add("value");
                    add("tariffPerDate");
                    add("paid");
                    add("published");
                    add("waterMeter");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class> () {{
                    add(NotNull.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("may not be null");
                }}.contains(constraintViolation.getMessage())
            );
        }
    }
}
