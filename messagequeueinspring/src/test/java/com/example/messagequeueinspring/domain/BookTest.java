package com.example.messagequeueinspring.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    public void newEmptyNameBook_WillValidatorSizeGreaterThan0(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Book>> violations = validator.validate(new Book(1, "", 1));
        assertTrue(violations.size() > 0);
    }
}