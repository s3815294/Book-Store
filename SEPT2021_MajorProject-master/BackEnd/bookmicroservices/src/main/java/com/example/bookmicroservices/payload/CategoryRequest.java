package com.example.bookmicroservices.payload;

import com.example.bookmicroservices.model.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class CategoryRequest {
    private long id;
    private String name;
    @JsonProperty("book")
    private List<Book> book = new ArrayList<>();

    public CategoryRequest(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBook() {
        return book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
