package com.example.bookmicroservices.validator;

import com.example.bookmicroservices.model.Review;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReviewValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Review.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Review review = (Review) object;

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
