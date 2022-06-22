package com.example.bookmicroservices.validator;

import com.example.bookmicroservices.model.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;


class ReviewValidatorTest {
    private ReviewValidator reviewValidator;
    @Test
    @DisplayName("Test should pass if valid review")
    void shouldPassWithContentCorrectLength() {
        reviewValidator = new ReviewValidator();
        Review review = new Review();
        review.setContent("A string less than 255 characters");
        review.setRating(3);
        Errors errors = new BeanPropertyBindingResult(review, "review");
        reviewValidator.validate(review, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should pass if valid review")
    void shouldPassWithExactLength() {
        reviewValidator = new ReviewValidator();
        Review review = new Review();
        review.setContent("aaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaa");
        review.setRating(3);
        Errors errors = new BeanPropertyBindingResult(review, "review");
        reviewValidator.validate(review, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should fail if invalid review")
    void shouldFailWithContentIncorrectLength() {
        reviewValidator = new ReviewValidator();
        Review review = new Review();
        review.setContent("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        review.setRating(3);
        Errors errors = new BeanPropertyBindingResult(review, "review");
        reviewValidator.validate(review, errors);

        Assertions.assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should pass if valid review")
    void shouldPassWithValidRating() {
        reviewValidator = new ReviewValidator();
        Review review = new Review();
        review.setContent("A string less than 255 characters");
        review.setRating(1);
        Errors errors = new BeanPropertyBindingResult(review, "review");
        reviewValidator.validate(review, errors);

        Assertions.assertFalse(errors.hasErrors());
    }
    @Test
    @DisplayName("Testing rating set to one")
    void shouldPassWithValidRatingOne() {
        reviewValidator = new ReviewValidator();
        Review review = new Review();
        review.setContent("A string less than 255 characters");
        review.setRating(1);
        Errors errors = new BeanPropertyBindingResult(review, "review");
        reviewValidator.validate(review, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Testing rating of value 5")
    void shouldPassWithValidRatingFive() {
        reviewValidator = new ReviewValidator();
        Review review = new Review();
        review.setContent("A string less than 255 characters");
        review.setRating(5);
        Errors errors = new BeanPropertyBindingResult(review, "review");
        reviewValidator.validate(review, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Testing rating above value 5")
    void shouldFailWithInvalidRatingAboveFive() {
        reviewValidator = new ReviewValidator();
        Review review = new Review();
        review.setContent("A string less than 255 characters");
        review.setRating(6);
        Errors errors = new BeanPropertyBindingResult(review, "review");
        reviewValidator.validate(review, errors);

        Assertions.assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Test rating below value 1")
    void shouldFailWithInvalidRatingBelowZero() {
        reviewValidator = new ReviewValidator();
        Review review = new Review();
        review.setContent("A string less than 255 characters");
        review.setRating(-1);
        Errors errors = new BeanPropertyBindingResult(review, "review");
        reviewValidator.validate(review, errors);

        Assertions.assertTrue(errors.hasErrors());
    }
}