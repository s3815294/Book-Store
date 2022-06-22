package com.example.ordermicroservices.model;



import com.example.ordermicroservices.utility.SingleOrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

// A single order for a user account which can have many books and one order account.
@Entity
public class SingleOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_account_id", nullable=false)
    private OrderAccount orderAccount;
    @OneToMany(mappedBy="singleOrder", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<OrderBook> orderBooks = new HashSet<>();
    private BigDecimal orderTotal;
    @Enumerated(EnumType.STRING)
    private SingleOrderStatus singleOrderStatus;
    private Date create_At;
    private Date update_At;

    public SingleOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderAccount getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(OrderAccount orderAccount) {
        this.orderAccount = orderAccount;
    }

    public Set<OrderBook> getBookDetails() {
        return orderBooks;
    }

    public void setBookDetails(Set<OrderBook> orderBooks) {
        this.orderBooks = orderBooks;
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

    public Set<OrderBook> getOrderBooks() {
        return orderBooks;
    }

    public void setOrderBooks(Set<OrderBook> orderBooks) {
        this.orderBooks = orderBooks;
    }

    public void setOrderBooks(List<OrderBook> orderBooks) {
        for (OrderBook book : orderBooks) {
            this.orderBooks.add(book);
        }
    }

    public SingleOrderStatus getSingleOrderStatus() {
        return singleOrderStatus;
    }

    public void setSingleOrderStatus(SingleOrderStatus singleOrderStatus) {
        this.singleOrderStatus = singleOrderStatus;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(List<OrderBook> orderBooks) {
        BigDecimal newTotal = BigDecimal.ZERO;
        for(OrderBook books : orderBooks) {
            newTotal= newTotal.add(books.getPrice().multiply(books.getQuantity()));
        }
        this.orderTotal = newTotal;
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