package com.rmit.sept.bk_loginservices.services;

import com.rmit.sept.bk_loginservices.Repositories.UserRepository;
import com.rmit.sept.bk_loginservices.Repositories.UserReviewRepository;
import com.rmit.sept.bk_loginservices.exceptions.UserReviewAlreadyExistsException;
import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.model.UserReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserReviewService {

    @Autowired
    private UserReviewRepository userReviewRepository;

    @Autowired
    private UserRepository userRepository;

    public UserReview saveUserReview (UserReview userReview) {
        try {
            User userToAdd = userRepository.findUserById(userReview.getUserId());
            userReview.setUser(userToAdd);
            return userReviewRepository.save(userReview);
        } catch (Exception e) {
            throw new UserReviewAlreadyExistsException("Review '" + userReview.getTitle() + "' already exists");
        }
    }
}
