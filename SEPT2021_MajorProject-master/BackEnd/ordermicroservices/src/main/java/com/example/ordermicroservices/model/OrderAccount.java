package com.example.ordermicroservices.model;



import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
// Model of user Account, this signifies a single uses account, has one to many orders relation.
@Entity
@Table(name = "order_account")
public class OrderAccount {
    @Id
    @NotNull(message = "account id is required")
    private Long id;
    @NotBlank(message = "Username is required")
    private String username;

    // One to Many with orders
    @OneToMany(mappedBy="orderAccount")
    @JsonBackReference
    private Set<SingleOrder> singleOrders;

    private Date create_At;
    private Date update_At;

    public OrderAccount() {
    }

    public OrderAccount(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<SingleOrder> getSingleOrders() {
        return singleOrders;
    }

    public void setSingleOrders(Set<SingleOrder> singleOrders) {
        this.singleOrders = singleOrders;
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