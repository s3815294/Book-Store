package com.rmit.sept.bk_loginservices.validator;

import com.rmit.sept.bk_loginservices.model.UserReview;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserReviewValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserReview.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UserReview review = (UserReview) object;

        if (review.getContent() != null) {
            if (review.getContent().length() >= 255) {
                errors.rejectValue("content","Value", "Content must be less than 255 characters");
            }
        }

        if (review.getRating() < 1 || review.getRating() > 5) {
            errors.rejectValue("rating","Value", "Rating must be between 1 and 5");
        }

    }
}
