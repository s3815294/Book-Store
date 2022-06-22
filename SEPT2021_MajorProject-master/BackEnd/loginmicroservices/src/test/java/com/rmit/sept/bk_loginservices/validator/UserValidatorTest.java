package com.rmit.sept.bk_loginservices.validator;

import com.rmit.sept.bk_loginservices.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;


class UserValidatorTest {

    private UserValidator userValidator;
    private User user;
    @Test
    @DisplayName("Test should pass if password is greater than 6 characters, and confirm password equals password")
    void shouldPassWithCorrectPassword() {
        userValidator = new UserValidator();
        user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setUsername("testuser@test.com");
        user.setPassword("password");
        user.setConfirmPassword("password");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should fail if password is less than 6 characters")
    void shouldFailIfPasswordTooShort() {
        userValidator = new UserValidator();
        user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setUsername("testuser@test.com");
        user.setPassword("p");
        user.setConfirmPassword("p");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user, errors);
        Assertions.assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should fail if password and confirmPassword don't match")
    void shouldFailIfPasswordsDontMatch() {
        userValidator = new UserValidator();
        user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setUsername("testuser@test.com");
        user.setPassword("password");
        user.setConfirmPassword("notapassword");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user, errors);
        Assertions.assertTrue(errors.hasErrors());
    }
}