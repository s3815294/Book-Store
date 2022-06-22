package com.rmit.sept.bk_loginservices.payload;

import com.rmit.sept.bk_loginservices.utility.UserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AdminBlockRequest {
    private Long userId;
    @Enumerated(EnumType.STRING)
    private UserRole adminRole;
    private boolean blockAccount;

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

    public boolean isBlockAccount() {
        return blockAccount;
    }

    public void setBlockAccount(boolean blockAccount) {
        this.blockAccount = blockAccount;
    }
}
