package com.rmit.sept.bk_loginservices.patterns;

import com.rmit.sept.bk_loginservices.model.AdminUser;
import com.rmit.sept.bk_loginservices.model.PublisherUser;
import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.payload.UserPayload;
import com.rmit.sept.bk_loginservices.utility.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User getUserPayload(UserPayload user) {
        if (user.getUserRole() == null) {
            return null;
        }
        if (user.getUserRole() == UserRole.CUSTOMER) {
            return new User(user);
        } else if (user.getUserRole() == UserRole.PUBLISHER) {
            PublisherUser setPublisher = new PublisherUser(user);
            User setUser = new User(user);
            setUser.setPublisherUser(setPublisher);
            return setUser;
        } else if (user.getUserRole() == UserRole.ADMIN) {
            AdminUser setAdmin = new AdminUser(user);
            User setUser = new User(user);
            setUser.setAdminUser(setAdmin);
            return setUser;
        }

        return null;
    }


}
