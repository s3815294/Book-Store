package com.example.bookmicroservices.model;



import com.example.bookmicroservices.utility.BookCondition;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import javax.validation.constraints.NotNull;

// Book model has many to many relation with categories.
@Entity
@Table(name = "book")
public class Book {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "author is required")
    private String author;
    @NotBlank(message = "ISBN is required")
    private String isbn;
    @Digits(integer = 5, fraction = 2, message = "price is required")
    private BigDecimal price;
    @Column(name = "book_condition")
    @Enumerated(EnumType.STRING)
    private BookCondition bookCondition;
    @NotBlank(message = "Seller name is required")
    private String sellerName;
    @NotNull(message = "Seller id is required")
    private Long sellerId;
    private String imageType;

    private Date create_At;
    private Date update_At;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id",
                    referencedColumnName = "id"))
    @JsonBackReference
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy="book", cascade = CascadeType.REMOVE)
    private Set<Review> reviews = new HashSet<>();

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public BookCondition getBookCondition() {
        return bookCondition;
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

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType; }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }



}