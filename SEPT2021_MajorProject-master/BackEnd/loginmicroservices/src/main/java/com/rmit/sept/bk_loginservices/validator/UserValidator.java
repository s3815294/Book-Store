package com.rmit.sept.bk_loginservices.validator;

import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.utility.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// Validates that a user class has a password greater than 6 characters and matching confirm password.
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;

        if(user.getPassword().length() <6){
            errors.rejectValue("password","Length", "Password must be at least 6 characters");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Match", "Passwords must match");
        }

        if (user.getUserRole() == UserRole.PUBLISHER && user.getPublisherUser().getAbn().length() == 0) {
            errors.rejectValue("abn","Null", "A publisher must have an ABN");
        }

        //confirmPassword
    }
}
