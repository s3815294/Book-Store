package com.example.ordermicroservices.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

// Order request payload as json cannot be sent via hashset.
public class OrderRequest {
    @NotNull(message = "username is required")
    private Long userId;
    @NotBlank(message = "username is required")
    private String username;

    @JsonProperty("booksOrdered")
    List<OrderBookRequest> booksOrdered = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderBookRequest> getBooksOrdered() {
        return booksOrdered;
    }

    public void setBooksOrdered(List<OrderBookRequest> booksOrdered) {
        this.booksOrdered = booksOrdered;
    }
}