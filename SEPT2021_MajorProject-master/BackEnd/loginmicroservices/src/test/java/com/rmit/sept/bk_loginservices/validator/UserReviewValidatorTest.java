package com.rmit.sept.bk_loginservices.validator;

import com.rmit.sept.bk_loginservices.model.UserReview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;


class UserReviewValidatorTest {
    private UserReviewValidator userReviewValidator = new UserReviewValidator();

    @Test
    @DisplayName("Test should pass if valid user review")
    void shouldPassWithContentCorrectLength() {
        UserReview userReview = new UserReview();
        userReview.setContent("A string less than 255 characters");
        userReview.setRating(3);
        Errors errors = new BeanPropertyBindingResult(userReview, "userReview");
        userReviewValidator.validate(userReview, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should pass if valid user review")
    void shouldPassWithExactLength() {
        UserReview userReview = new UserReview();
        userReview.setContent("aaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaa");
        userReview.setRating(3);
        Errors errors = new BeanPropertyBindingResult(userReview, "userReview");
        userReviewValidator.validate(userReview, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should fail if invalid user review")
    void shouldFailWithContentIncorrectLength() {
        UserReview userReview = new UserReview();
        userReview.setContent("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        userReview.setRating(3);
        Errors errors = new BeanPropertyBindingResult(userReview, "userReview");
        userReviewValidator.validate(userReview, errors);

        Assertions.assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should pass if valid user review")
    void shouldPassWithValidRating() {
        UserReview userReview = new UserReview();
        userReview.setContent("A string less than 255 characters");
        userReview.setRating(1);
        Errors errors = new BeanPropertyBindingResult(userReview, "userReview");
        userReviewValidator.validate(userReview, errors);

        Assertions.assertFalse(errors.hasErrors());
    }
    @Test
    @DisplayName("Testing rating set to one")
    void shouldPassWithValidRatingOne() {
        UserReview userReview = new UserReview();
        userReview.setContent("A string less than 255 characters");
        userReview.setRating(1);
        Errors errors = new BeanPropertyBindingResult(userReview, "userReview");
        userReviewValidator.validate(userReview, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Testing rating of value 5")
    void shouldPassWithValidRatingFive() {
        UserReview userReview = new UserReview();
        userReview.setContent("A string less than 255 characters");
        userReview.setRating(5);
        Errors errors = new BeanPropertyBindingResult(userReview, "userReview");
        userReviewValidator.validate(userReview, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Testing rating above value 5")
    void shouldFailWithInvalidRatingAboveFive() {
        userReviewValidator = new UserReviewValidator();
        UserReview userReview = new UserReview();
        userReview.setContent("A string less than 255 characters");
        userReview.setRating(6);
        Errors errors = new BeanPropertyBindingResult(userReview, "userReview");
        userReviewValidator.validate(userReview, errors);

        Assertions.assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Test rating below value 1")
    void shouldFailWithInvalidRatingBelowZero() {
        userReviewValidator = new UserReviewValidator();
        UserReview userReview = new UserReview();
        userReview.setContent("A string less than 255 characters");
        userReview.setRating(-1);
        Errors errors = new BeanPropertyBindingResult(userReview, "userReview");
        userReviewValidator.validate(userReview, errors);

        Assertions.assertTrue(errors.hasErrors());
    }
}