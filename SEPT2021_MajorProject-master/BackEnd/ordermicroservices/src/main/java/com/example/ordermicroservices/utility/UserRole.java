package com.example.ordermicroservices.utility;

import com.fasterxml.jackson.annotation.JsonCreator;

// Enum for user roles used in user class.
public enum UserRole {
    CUSTOMER("customer"),
    PUBLISHER("publisher"),
    ADMIN("admin");

    private String userCode;

    private UserRole(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return this.userCode;
    }
    @JsonCreator
    public static UserRole getUserRoleCode(String value) {
        for (UserRole role : UserRole.values()) {
            if (role.getUserCode().equals(value)) {
                return role;
            }
        }
        return null;
    }
}