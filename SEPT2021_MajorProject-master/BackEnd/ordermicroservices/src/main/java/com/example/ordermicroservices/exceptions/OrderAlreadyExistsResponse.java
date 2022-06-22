package com.example.ordermicroservices.exceptions;

public class OrderAlreadyExistsResponse {

    private String title;

    public OrderAlreadyExistsResponse(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}