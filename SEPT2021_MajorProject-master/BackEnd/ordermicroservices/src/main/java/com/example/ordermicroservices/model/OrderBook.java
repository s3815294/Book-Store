package com.example.ordermicroservices.model;

import com.example.ordermicroservices.payload.OrderBookRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

// many books relate to a single order.
@Entity
@Table(name = "order_book")
public class OrderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Date create_At;
    private Date update_At;
    @Digits(integer = 5, fraction = 0, message = "Quantity is required")
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name="single_order_id", nullable=false)
    @JsonBackReference
    private SingleOrder singleOrder;

    public OrderBook() {
    }

    public OrderBook(OrderBookRequest orderBookRequest) {
        this.bookId = orderBookRequest.getBookId();
        this.title = orderBookRequest.getTitle();
        this.author = orderBookRequest.getAuthor();
        this.isbn = orderBookRequest.getIsbn();
        this.price = orderBookRequest.getPrice();
        this.sellerName = orderBookRequest.getSellerName();
        this.sellerId = orderBookRequest.getSellerId();
        this.quantity = orderBookRequest.getQuantity();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public SingleOrder getSingleOrder() {
        return singleOrder;
    }

    public void setSingleOrder(SingleOrder singleOrder) {
        this.singleOrder = singleOrder;
    }

    public BigDecimal getQuantity() {return quantity; }

    public void setQuantity(BigDecimal quantity) {this.quantity = quantity; }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }
}