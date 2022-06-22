package com.rmit.sept.bk_loginservices.Repositories;

import com.rmit.sept.bk_loginservices.model.PublisherUser;
import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.utility.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findUserById(Long id);
    List<User> findAllByPublisherUser_BusinessApprovalAndUserRole(Boolean businessApproval, UserRole role);
}
