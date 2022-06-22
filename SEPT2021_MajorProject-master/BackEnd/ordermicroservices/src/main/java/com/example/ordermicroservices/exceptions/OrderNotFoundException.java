package com.example.ordermicroservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderNotFoundException extends RuntimeException  {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
