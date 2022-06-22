package com.example.bookmicroservices.validator;

import com.example.bookmicroservices.utility.BookCondition;
import com.example.bookmicroservices.payload.BookRequest;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class BookValidatorTest {
    private BookValidator bookValidator;
    private BookRequest book;

    @Test
    @DisplayName("Test should pass if valid book")
    void shouldPassWithCorrectBookDetails() {
        bookValidator = new BookValidator();
        book = new BookRequest();
        List<String> categories = new ArrayList<>();
        categories.add("Fiction");
        categories.add("Free");
        book.setTitle("TestTitle");
        book.setAuthor("TestAuthor");
        book.setIsbn("123abc");
        book.setPrice(BigDecimal.valueOf(49.99));
        book.setCategories(categories);
        book.setBookCondition(BookCondition.NEW);
        Errors errors = new BeanPropertyBindingResult(book, "book");
        bookValidator.validate(book, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should fail with that has invalid category")
    void shouldFailWithInvalidCategory() {
        bookValidator = new BookValidator();
        book = new BookRequest();
        List<String> categories = new ArrayList<>();
        categories.add("Some Random Category");
        book.setTitle("TestTitle");
        book.setAuthor("TestAuthor");
        book.setIsbn("123abc");
        book.setPrice(BigDecimal.valueOf(49.99));
        book.setCategories(categories);
        book.setBookCondition(BookCondition.NEW);
        Errors errors = new BeanPropertyBindingResult(book, "book");
        bookValidator.validate(book, errors);

        Assertions.assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should fail that has negative price")
    void shouldFailWithNegativePrice() {
        bookValidator = new BookValidator();
        book = new BookRequest();
        List<String> categories = new ArrayList<>();
        categories.add("Fiction");
        categories.add("Free");
        book.setTitle("TestTitle");
        book.setAuthor("TestAuthor");
        book.setIsbn("123abc");
        book.setPrice(BigDecimal.valueOf(-50));
        book.setCategories(categories);
        book.setBookCondition(BookCondition.NEW);
        Errors errors = new BeanPropertyBindingResult(book, "book");
        bookValidator.validate(book, errors);

        Assertions.assertTrue(errors.hasErrors());
    }
}