package com.example.bookmicroservices.payload;

import com.example.bookmicroservices.model.Book;
import com.example.bookmicroservices.utility.BookCondition;
import com.example.bookmicroservices.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

// BookRequest class allows for json conversion of categories as hashset is not accepted in json.
public class BookRequest {

    private long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @NotBlank(message = "ISBN is required")
    private String isbn;
    @Digits(integer = 5, fraction = 2, message = "price is required")
    private BigDecimal price;
    @JsonProperty("category")
    private List<String> categories = new ArrayList<>();
    private BookCondition bookCondition;
    @NotBlank(message = "Seller name is required")
    private String sellerName;
    @NotNull(message = "Seller id is required")
    private Long sellerId;
    private String imageType;

    private Date create_At;
    private Date update_At;

    public BookRequest() {

    }

    public BookRequest(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.price = book.getPrice();
        this.create_At = book.getCreate_At();
        this.update_At = book.getUpdate_At();
        for (Category category : book.getCategories()) {
            this.categories.add(category.getName());
        }
        this.bookCondition = book.getBookCondition();
        this.imageType = book.getImageType();
        this.sellerId = book.getSellerId();
        this.sellerName = book.getSellerName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }

    public BookCondition getBookCondition() {
        return bookCondition;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setBookCondition(BookCondition bookCondition) {
        this.bookCondition = bookCondition;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public BookRequest updateWith(BookRequest book) {
        this.setTitle(book.title);
        this.setAuthor(book.author);
        this.setIsbn(book.isbn);
        this.setPrice(book.price);
        this.setBookCondition(book.bookCondition);
        this.setSellerName(book.sellerName);
        this.setSellerId(book.sellerId);
        this.setCategories(book.categories);

        return (this);
    }
}