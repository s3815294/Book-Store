package com.rmit.sept.bk_loginservices.model;

import com.rmit.sept.bk_loginservices.payload.AdminUserRequest;
import com.rmit.sept.bk_loginservices.payload.UserPayload;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class PublisherUser extends User {
    @NotBlank(message = "ABN is required")
    private String abn;
    private boolean businessApproval;

    public PublisherUser() {

    }
    public PublisherUser(User user) {
        super.setAddress(user.getUsername());
        super.setFirstName(user.getFirstName());
        super.setLastName(user.getLastName());
        super.setAddress(user.getAddress());
        super.setPassword(user.getPassword());
        super.setConfirmPassword(user.getConfirmPassword());
        super.setPostcode(user.getPostcode());
        super.setCountry(user.getCountry());
        super.setPhone(user.getPhone());
        super.setUserRole(user.getUserRole());
        super.setBlockAccount(user.isBlockAccount());
        this.abn = user.getPublisherUser().getAbn();
        this.businessApproval = user.getPublisherUser().isBusinessApproval();
    }

    public PublisherUser(UserPayload user) {
        super.setAddress(user.getUsername());
        super.setFirstName(user.getFirstName());
        super.setLastName(user.getLastName());
        super.setAddress(user.getAddress());
        super.setPassword(user.getPassword());
        super.setConfirmPassword(user.getConfirmPassword());
        super.setPostcode(user.getPostcode());
        super.setCountry(user.getCountry());
        super.setPhone(user.getPhone());
        super.setUserRole(user.getUserRole());
        super.setBlockAccount(user.isBlockAccount());
        this.abn = user.getAbn();
        this.businessApproval = user.isBusinessApproval();
    }

    public String getAbn() {
        return abn;
    }

    public void setAbn(String abn) {
        this.abn = abn;
    }

    public boolean isBusinessApproval() {
        return businessApproval;
    }

    public void setBusinessApproval(boolean businessApproval) {
        this.businessApproval = businessApproval;
    }
}
