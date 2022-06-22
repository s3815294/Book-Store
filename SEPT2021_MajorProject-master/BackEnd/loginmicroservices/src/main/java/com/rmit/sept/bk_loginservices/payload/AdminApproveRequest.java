package com.rmit.sept.bk_loginservices.payload;

import com.rmit.sept.bk_loginservices.utility.UserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AdminApproveRequest {
    private Long userId;
    @Enumerated(EnumType.STRING)
    private UserRole adminRole;
    private boolean businessApproval;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(UserRole adminRole) {
        this.adminRole = adminRole;
    }

    public boolean isBusinessApproval() {
        return businessApproval;
    }

    public void setBusinessApproval(boolean businessApproval) {
        this.businessApproval = businessApproval;
    }
}
