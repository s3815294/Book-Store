package com.example.ordermicroservices.validator;

import com.example.ordermicroservices.model.SingleOrder;
import com.example.ordermicroservices.payload.OrderBookRequest;
import com.example.ordermicroservices.payload.OrderRequest;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancelOrderValidatorTest {
    private CancelOrderValidator cancelOrderValidator = new CancelOrderValidator();


    @Test
    @DisplayName("Test should pass with valid cancel book date")
    void shouldPassValidPrice() {
        SingleOrder order = new SingleOrder();
        order.setCreate_At(new Date());
        Errors errors = new BeanPropertyBindingResult(order, "order");
        cancelOrderValidator.validate(order, errors);
        Assert.assertFalse(errors.hasErrors());
    }
    @Test
    @DisplayName("Test should fail with invalid cancel book date of 2 hours.")
    void shouldFailMissingPrice() {
        SingleOrder order = new SingleOrder();
        order.setCreate_At(new Date((System.currentTimeMillis() - 3600 * 1000)*2));

        Errors errors = new BeanPropertyBindingResult(order, "order");
        cancelOrderValidator.validate(order, errors);
        Assert.assertTrue(errors.hasErrors());
    }

}