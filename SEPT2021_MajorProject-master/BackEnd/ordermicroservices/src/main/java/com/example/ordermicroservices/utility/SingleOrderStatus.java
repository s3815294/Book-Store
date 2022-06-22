package com.example.ordermicroservices.utility;

import com.fasterxml.jackson.annotation.JsonCreator;

// This is the book condition enum used in the book model.
public enum SingleOrderStatus {
    PENDING("new"),
    COMPLETE("used"),
    CANCELLED("cancelled");

    private String orderCode;

    private SingleOrderStatus(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    @JsonCreator
    public static SingleOrderStatus getOrderCondfromCode(String value) {
        for (SingleOrderStatus cond : SingleOrderStatus.values()) {
            if (cond.getOrderCode().equals(value)) {
                return cond;
            }
        }
        return null;
    }
}


