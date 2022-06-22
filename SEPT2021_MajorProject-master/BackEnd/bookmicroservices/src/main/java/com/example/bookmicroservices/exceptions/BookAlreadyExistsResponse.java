package com.example.bookmicroservices.exceptions;

public class BookAlreadyExistsResponse {

    private String title;

    public BookAlreadyExistsResponse(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}