package com.example.bookmicroservices.exceptions;

public class CategoryAlreadyExistsResponse {
    private String title;
    public CategoryAlreadyExistsResponse(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
