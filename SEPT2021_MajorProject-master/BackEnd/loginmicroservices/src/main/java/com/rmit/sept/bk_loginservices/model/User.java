package com.rmit.sept.bk_loginservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.sept.bk_loginservices.payload.AdminUserRequest;
import com.rmit.sept.bk_loginservices.payload.EditAdminUserRequest;
import com.rmit.sept.bk_loginservices.payload.UserPayload;
import com.rmit.sept.bk_loginservices.utility.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "username is required")
    @Column(unique = true)
    @Pattern(regexp="^(.+)@(.+)$",message="Please enter a valid email address")
    private String username;
    @NotBlank(message = "Please enter your first name")
    @Pattern(regexp="^[a-zA-Z\\s]+$",message="Please enter a valid first name")
    private String firstName;
    @NotBlank(message = "Please enter your last name")
    @Pattern(regexp="^[a-zA-Z\\s]+$",message="Please enter a valid last name")
    private String lastName;
    @NotBlank(message = "Password field is required")
    private String password;
    @Transient
    private String confirmPassword;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "country is required")
    @Pattern(regexp="^[a-zA-Z\\s]+$",message="Please enter a valid country")
    private String country;
    @NotNull(message = "postcode is required")
    @Pattern(regexp="^[0-9]{4}$",message="Please enter a valid postcode")
    private String postcode;
    @NotBlank(message = "phone is required")
    @Pattern(regexp="^[0-9]+$",message="Please enter a valid phone number")
    private String phone;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private boolean blockAccount;
    @OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
    private Set<UserReview> userReviews = new HashSet<>();

    @Embedded
    private PublisherUser publisherUser;
    @Embedded
    private AdminUser adminUser;

    private Date create_At;
    private Date update_At;

    //OneToMany with Project

    public User() {
    }

    public User(UserPayload user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.confirmPassword = user.getConfirmPassword();
        this.address = user.getAddress();
        this.country = user.getCountry();
        this.postcode = user.getPostcode();
        this.phone = user.getPhone();
        this.userRole = user.getUserRole();
        this.blockAccount = user.isBlockAccount();
    }
    
    public User(AdminUserRequest adminUserRequest) {
        this.username = adminUserRequest.getUsername();
        this.firstName = adminUserRequest.getFirstName();
        this.lastName = adminUserRequest.getLastName();
        this.password = adminUserRequest.getPassword();
        this.confirmPassword = adminUserRequest.getConfirmPassword();
        this.address = adminUserRequest.getAddress();
        this.country = adminUserRequest.getCountry();
        this.postcode = adminUserRequest.getPostcode();
        this.phone = adminUserRequest.getPhone();
        this.userRole = adminUserRequest.getUserRole();
        this.blockAccount = adminUserRequest.isBlockAccount();
    }

    public User(EditAdminUserRequest editAdminUserRequest) {
        this.firstName = editAdminUserRequest.getFirstName();
        this.lastName = editAdminUserRequest.getLastName();
        this.address = editAdminUserRequest.getAddress();
        this.phone = editAdminUserRequest.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isBlockAccount() {
        return blockAccount;
    }

    public void setBlockAccount(boolean blockAccount) {
        this.blockAccount = blockAccount;
    }

    public Set<UserReview> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(Set<UserReview> userReviews) {
        this.userReviews = userReviews;
    }

    public PublisherUser getPublisherUser() {
        return publisherUser;
    }

    public void setPublisherUser(PublisherUser publisherUser) {
        this.publisherUser = publisherUser;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }

    /*
    UserDetails interface methods
     */

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}