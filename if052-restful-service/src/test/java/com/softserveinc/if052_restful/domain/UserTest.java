package com.softserveinc.if052_restful.domain;

import com.softserveinc.if052_restful.model.AbstractModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by valentyn on 2/18/15.
 */
public class UserTest extends AbstractModel{
    /*
     * Test field validation for entity correct
     */
    @Test
    public void testUserFieldSuccess(){

        Set < ConstraintViolation < User > > constraintViolationSet;
        //- Success -//
        //- Create entity-//
        User userSuccess = new User(
            "Valentyn",
            "Namisnyk",
            "Yaroslavovuch",
            "Valentine1996",
            "Valentine1996",
            "valentine@gmail.com"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( userSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }

    /*
    * Test field validation for entity failure(NotNull)
    */
    @Test
    public void testUserFieldFailureNotNull(){

        Set < ConstraintViolation < User > > constraintViolationSet;
        //- Failure: Incorrect user -//
        //- Create entity-//
        User userFailureNotNull = new User(
            null,
            null,
            null,
            null,
            null,
            null
        );
        //- Validate -//
        constraintViolationSet = validator.validate(userFailureNotNull);

        assertEquals( 12, constraintViolationSet.size() );

        for( ConstraintViolation < User > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("name");
                    add("surname");
                    add("middleName");
                    add("login");
                    add("password");
                    add("email");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class > () {{
                    add(NotNull.class);
                    add(NotEmpty.class);
                    add(Email.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add("may not be null");
                    add("may not be empty");
                }}.contains(constraintViolation.getMessage())
            );
        }
    }

    /*
    * Test field validation for entity failure(NotNull)
    */
    @Test
    public void testUserFieldFailureNotEmpty(){

        Set < ConstraintViolation < User > > constraintViolationSet;
        //- Failure: Empty name, surname, password -//
        //- Create entity-//
        User userFailureNotEmpty = new User(
            "",
            "",
            "",
            "Valentine1996",
            "Valentine1996",
            "valentine@gmail.com"
        );
        //- Validate -//
        constraintViolationSet = validator.validate(userFailureNotEmpty);

        assertEquals( 6, constraintViolationSet.size() );

        for( ConstraintViolation < User > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("name");
                    add("surname");
                    add("middleName");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class > () {{
                    add(NotEmpty.class);
                    add(Length.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add("may not be empty");
                    add("length must be between 2 and 32");
                }}.contains(constraintViolation.getMessage())
            );
        }

        //- Failure: Empty login, password-//
        //- Set correct full name and email, incorrect login and password-//
        userFailureNotEmpty.setName("Valentyn");
        userFailureNotEmpty.setSurname("Namisnyk");
        userFailureNotEmpty.setMiddleName("Yaroslavovuch");
        userFailureNotEmpty.setEmail("");
        userFailureNotEmpty.setLogin("");
        userFailureNotEmpty.setPassword("");

        constraintViolationSet = validator.validate(userFailureNotEmpty);

        assertEquals( 6, constraintViolationSet.size() );

        for( ConstraintViolation < User > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("login");
                    add("password");
                    add("email");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add(NotEmpty.class);
                    add(Length.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("may not be empty");
                    add("length must be between 8 and 32");
                }}.contains(constraintViolation.getMessage())
            );
        }
    }

    /*
    * Test field validation for entity failure(NotNull)
    */
    @Test
    public void testUserFieldFailureLength() {

        Set < ConstraintViolation < User > > constraintViolationSet;
        //- Failure: Overrun max length for name, surname, middleName-//
        //- Create entity-//
        User userFailureMaxLength = new User(
            "123456789012345678901234567890123",
            "123456789012345678901234567890123",
            "123456789012345678901234567890123",
            "Valentine1996",
            "valentine1996",
            "valentine@gmail.com"
        );
        //- Validate -//
        constraintViolationSet = validator.validate(userFailureMaxLength);

        assertEquals( 3, constraintViolationSet.size() );

        for( ConstraintViolation < User > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("name");
                    add("surname");
                    add("middleName");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add(NotEmpty.class);
                    add(Length.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("length must be between 2 and 32");
                }}.contains(constraintViolation.getMessage())
            );
        }
        //- Failure: Overrun max length for login, password -//
        //- Create entity-//
        userFailureMaxLength.setName("Valentyn");
        userFailureMaxLength.setSurname("Namisnyk");
        userFailureMaxLength.setMiddleName("Yaroslavovuch");
        userFailureMaxLength.setLogin("123456789012345678901234567890123");
        userFailureMaxLength.setPassword("123456789012345678901234567890123");
        userFailureMaxLength.setEmail("12345678901234567890123@gmail.com");
        //- Validate -//
        constraintViolationSet = validator.validate(userFailureMaxLength);

        assertEquals( 3, constraintViolationSet.size() );

        for( ConstraintViolation < User > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("login");
                    add("password");
                    add("email");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add(Length.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("length must be between 8 and 32");
                }}.contains(constraintViolation.getMessage())
            );
        }
    }
}
