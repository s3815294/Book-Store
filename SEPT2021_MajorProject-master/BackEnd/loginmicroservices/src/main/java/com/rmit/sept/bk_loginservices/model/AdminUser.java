package com.rmit.sept.bk_loginservices.model;

import com.rmit.sept.bk_loginservices.payload.UserPayload;

import javax.persistence.Embeddable;

@Embeddable
public class AdminUser extends User{

    public AdminUser() {}

    public AdminUser(UserPayload user) {
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
        super.setAdminUser(this);
    }

    public AdminUser(User user) {
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
        super.setAdminUser(this);
    }

}
