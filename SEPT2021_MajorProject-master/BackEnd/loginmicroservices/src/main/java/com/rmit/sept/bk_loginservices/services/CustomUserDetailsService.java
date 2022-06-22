package com.rmit.sept.bk_loginservices.services;

import com.rmit.sept.bk_loginservices.Repositories.UserRepository;
import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.payload.AdminUserRequest;
import com.rmit.sept.bk_loginservices.utility.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null) new UsernameNotFoundException("User not found");
        return user;
    }

    public User loadUserById(Long id){
        User user = userRepository.findUserById(id);
        if(user==null) new UsernameNotFoundException("User not found");
        return user;

    }

    public List<AdminUserRequest> getAllUsers() {
        try {
            List<User> users = (List<User>) userRepository.findAll();
            return convertUserClass(users);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Users not found");
        }
    }

    public AdminUserRequest getSingleUser(Long id) {
        try {
            User user =  userRepository.findUserById(id);
            return new AdminUserRequest(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public List<AdminUserRequest> getAllUsersPendingApproval() {
        try {
            List<User> users = userRepository.findAllByPublisherUser_BusinessApprovalAndUserRole(false,UserRole.PUBLISHER);
            return convertUserClass(users);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Users not found");
        }
    }


    private List<AdminUserRequest> convertUserClass(List<User> users) {
        List<AdminUserRequest> usersReturn = new ArrayList<>();
        if (users.size() != 0) {
            for (int i = 0; i < users.size(); i++) {
                usersReturn.add(new AdminUserRequest(users.get(i)));
            }
        }
        return usersReturn;
    }
}
