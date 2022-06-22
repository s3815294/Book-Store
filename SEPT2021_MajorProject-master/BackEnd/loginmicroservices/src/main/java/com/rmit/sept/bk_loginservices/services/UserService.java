package com.rmit.sept.bk_loginservices.services;

import com.rmit.sept.bk_loginservices.Repositories.UserRepository;
import com.rmit.sept.bk_loginservices.exceptions.InvalidUsernameException;
import com.rmit.sept.bk_loginservices.exceptions.UsernameAlreadyExistsException;
import com.rmit.sept.bk_loginservices.model.PublisherUser;
import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.patterns.UserFactory;
import com.rmit.sept.bk_loginservices.payload.AdminApproveRequest;
import com.rmit.sept.bk_loginservices.payload.AdminBlockRequest;
import com.rmit.sept.bk_loginservices.payload.EditAdminUserRequest;
import com.rmit.sept.bk_loginservices.payload.UserPayload;
import com.rmit.sept.bk_loginservices.utility.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserFactory userFactory;

    public User saveUser(User newUser) {

        /*
         * newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
         * //Username has to be unique (exception) // Make sure that password and
         * confirmPassword match // We don't persist or show the confirmPassword return
         * userRepository.save(newUser);
         */
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            // Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            // Set business approval values

            return userRepository.save(newUser);

        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
        }
    }

    public User setBlockStatus(AdminBlockRequest blockRequest) {
        try {
            User user = userRepository.findUserById(blockRequest.getUserId());
            user.setBlockAccount(blockRequest.isBlockAccount());
            return userRepository.save(user);
        } catch (Exception e) {
            throw new InvalidUsernameException("An error occurred retrieving user");
        }
    }

    public User setApproveStatus(AdminApproveRequest approveRequest) {
        try {
            User user = userRepository.findUserById(approveRequest.getUserId());
            user.getPublisherUser().setBusinessApproval(approveRequest.isBusinessApproval());
            return userRepository.save(user);
        } catch (Exception e) {
            throw new InvalidUsernameException("An error occurred retrieving user");
        }
    }

    public Boolean isAccountBlocked(String username) {
        try {
            User user = userRepository.findByUsername(username);
            return user.isBlockAccount();
        } catch (Exception e) {
            throw new InvalidUsernameException("An error occurred retrieving user");
        }
    }

    public Boolean isAccountApproved(String username) {
        try {
            User user = userRepository.findByUsername(username);
            // only publishers need approval.
            if (user.getUserRole() != UserRole.PUBLISHER) {
                return true;
            }
            return user.getPublisherUser().isBusinessApproval();
        } catch (Exception e) {
            throw new InvalidUsernameException("An error occurred retrieving user");
        }
    }

    public User update(EditAdminUserRequest updateUser) {
        try {
            User newUser = userRepository.findUserById(updateUser.getUserId());
            newUser.setFirstName(updateUser.getFirstName());
            newUser.setLastName(updateUser.getLastName());
            newUser.setAddress(updateUser.getAddress());
            newUser.setPhone(updateUser.getPhone());

            return userRepository.save(newUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UsernameNotFoundException("User not found");
        }
    }
}
