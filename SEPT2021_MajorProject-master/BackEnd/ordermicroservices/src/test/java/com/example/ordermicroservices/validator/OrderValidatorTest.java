package com.example.ordermicroservices.validator;

import com.example.ordermicroservices.payload.OrderBookRequest;
import com.example.ordermicroservices.payload.OrderRequest;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {
    private OrderValidator orderValidator = new OrderValidator();


    @Test
    @DisplayName("Test should pass with valid price")
    void shouldPassValidPrice() {
        OrderRequest order = new OrderRequest();
        List<OrderBookRequest> books = new ArrayList<>();
        OrderBookRequest book1 = new OrderBookRequest();
        book1.setAuthor("test Author");
        book1.setTitle("Test Title");
        book1.setIsbn("Test Isbn");
        book1.setPrice(BigDecimal.valueOf(20.99));
        books.add(book1);
        order.setBooksOrdered(books);
        Errors errors = new BeanPropertyBindingResult(order, "order");
        orderValidator.validate(order, errors);
        Assert.assertFalse(errors.hasErrors());
    }
    @Test
    @DisplayName("Test should fail pass with missing price")
    void shouldFailMissingPrice() {
        OrderRequest order = new OrderRequest();
        List<OrderBookRequest> books = new ArrayList<>();
        OrderBookRequest book1 = new OrderBookRequest();
        book1.setAuthor("test Author");
        book1.setTitle("Test Title");
        book1.setIsbn("Test Isbn");
        books.add(book1);
        order.setBooksOrdered(books);
        Errors errors = new BeanPropertyBindingResult(order, "order");
        orderValidator.validate(order, errors);
        Assert.assertTrue(errors.hasErrors());
    }

    @Test
    @DisplayName("Test should fail pass with invalid price")
    void shouldFailInvalidPrice() {
        OrderRequest order = new OrderRequest();
        List<OrderBookRequest> books = new ArrayList<>();
        OrderBookRequest book1 = new OrderBookRequest();
        book1.setAuthor("test Author");
        book1.setTitle("Test Title");
        book1.setIsbn("Test Isbn");
        books.add(book1);
        book1.setPrice(BigDecimal.valueOf(-20.99));
        order.setBooksOrdered(books);
        Errors errors = new BeanPropertyBindingResult(order, "order");
        orderValidator.validate(order, errors);
        Assert.assertTrue(errors.hasErrors());
    }
}