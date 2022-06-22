package com.example.ordermicroservices.payload;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

// order book payload for order request payload.
public class OrderBookRequest {
    @NotNull(message = "book id is required")
    private Long bookId;
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "author is required")
    private String author;
    @NotBlank(message = "ISBN is required")
    private String isbn;
    @Digits(integer = 5, fraction = 2, message = "price is required")
    private BigDecimal price;
    @NotBlank(message = "Seller name is required")
    private String sellerName;
    @NotNull(message = "Seller id is required")
    private Long sellerId;
    @Digits(integer = 5, fraction = 0, message = "Quantity is required")
    private BigDecimal quantity;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public BigDecimal getQuantity() {return quantity; }

    public void setQuantity(BigDecimal quantity) {this.quantity = quantity;}
}
