package com.example.bookmicroservices.utility;

import com.fasterxml.jackson.annotation.JsonCreator;

// This is the book condition enum used in the book model.
public enum BookCondition {
    NEW("new"),
    USED("used");

    private String bookCode;

    private BookCondition(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookCode() {
        return this.bookCode;
    }

    @JsonCreator
    public static BookCondition getBookCondfromCode(String value) {
        for (BookCondition cond : BookCondition.values()) {
            if (cond.getBookCode().equals(value)) {
                return cond;
            }
        }
        return null;
    }
}


