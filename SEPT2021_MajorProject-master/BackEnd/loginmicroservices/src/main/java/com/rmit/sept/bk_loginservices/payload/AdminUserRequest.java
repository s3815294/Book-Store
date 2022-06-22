package com.rmit.sept.bk_loginservices.payload;

import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.utility.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

// Validates an admin user add request payload.
public class AdminUserRequest extends UserPayload {

    @Enumerated(EnumType.STRING)
    private UserRole adminRole;

    public AdminUserRequest() {

    }
    public AdminUserRequest(User user) {
        super.setUserId(user.getId());
        super.setUsername(user.getUsername());
        super.setFirstName(user.getFirstName());
        super.setLastName(user.getLastName());
        super.setAddress(user.getAddress());
        super.setPhone(user.getPhone());
        super.setBlockAccount(user.isBlockAccount());
        super.setUserRole(user.getUserRole());
        super.setBusinessApproval(user.getPublisherUser().isBusinessApproval());
    }


    public UserRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(UserRole adminRole) {
        this.adminRole = adminRole;
    }
}
