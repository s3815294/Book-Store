package com.rmit.sept.bk_loginservices.web;


import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.model.UserReview;
import com.rmit.sept.bk_loginservices.patterns.UserFactory;
import com.rmit.sept.bk_loginservices.payload.*;
import com.rmit.sept.bk_loginservices.security.JwtTokenProvider;
import com.rmit.sept.bk_loginservices.services.CustomUserDetailsService;
import com.rmit.sept.bk_loginservices.services.MapValidationErrorService;
import com.rmit.sept.bk_loginservices.services.UserReviewService;
import com.rmit.sept.bk_loginservices.services.UserService;
import com.rmit.sept.bk_loginservices.utility.UserRole;
import com.rmit.sept.bk_loginservices.validator.UserReviewValidator;
import com.rmit.sept.bk_loginservices.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rmit.sept.bk_loginservices.security.SecurityConstant.TOKEN_PREFIX;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserReviewService userReviewService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserReviewValidator userReviewValidator;

    @Autowired
    private UserFactory userFactory;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserPayload user, BindingResult result){
        User newUser = userFactory.getUserPayload(user);
        // Validate passwords match
        userValidator.validate(newUser,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        User returnUser = userService.saveUser(newUser);

        return  new ResponseEntity<>(returnUser, HttpStatus.CREATED);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<?> adminAddUser(@Valid @RequestBody AdminUserRequest adminUserRequest, BindingResult result){
        // Validates that the user is an admin before adding the user.
        if (adminUserRequest.getAdminRole() != UserRole.ADMIN) {
            return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
        }
        User user = userFactory.getUserPayload(adminUserRequest);
        // validates the add user data.

        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        User newUser = userService.saveUser(user);

        return  new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> update(@Valid @RequestBody EditAdminUserRequest editAdminUserRequest,
                                    BindingResult result) {
        if (UserRole.getUserRoleCode(editAdminUserRequest.getRole()) != UserRole.ADMIN) {
            return  new ResponseEntity<>("User Must be admin", HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        // validate the user

        User updateUser = userService.update(editAdminUserRequest);

        return  new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){

        if (userService.isAccountBlocked(loginRequest.getUsername())) {
            result.addError(new FieldError("LoginRequest", "blocked", "User is blocked"));
        } else if (!userService.isAccountApproved(loginRequest.getUsername())) {
            result.addError(new FieldError("LoginRequest", "blocked", "Account is awaiting approval by Admin"));
        }
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }

    // this method accepts a review and adds it to a user.
    @PostMapping(value = "/review")
    public ResponseEntity<?> addReview(@RequestBody UserReview userReview, BindingResult result) {
        // validate the review
        userReviewValidator.validate(userReview,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        UserReview user = userReviewService.saveUserReview(userReview);
        return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/admin/block")
    public ResponseEntity<?> adminBlockUser(@RequestBody AdminBlockRequest adminBlockRequest, BindingResult result) {
        // Validates that the user is an admin before adding the user.
        if (adminBlockRequest.getAdminRole() != UserRole.ADMIN) {
            result.addError(new FieldError("User", "role", "Invalid request - Admin Only"));
        }
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        User user = userService.setBlockStatus(adminBlockRequest);

        // return a stripped user to protect data.
        AdminUserRequest requestValue = new AdminUserRequest(user);
        return new ResponseEntity<>(requestValue, HttpStatus.OK);
    }

    @PostMapping("/admin/approve")
    public ResponseEntity<?> adminApproveUser(@RequestBody AdminApproveRequest adminApproveRequest, BindingResult result) {
        // Validates that the user is an admin before adding the user.
        if (adminApproveRequest.getAdminRole() != UserRole.ADMIN) {
            result.addError(new FieldError("User", "role", "Invalid request - Admin Only"));
        }
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        User user = userService.setApproveStatus(adminApproveRequest);

        // return a stripped user to protect data.
        AdminUserRequest requestValue = new AdminUserRequest(user);
        return new ResponseEntity<>(requestValue, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){

        // gets all users in the system.
        List<AdminUserRequest> users = customUserDetailsService.getAllUsers();
        if (users.size() == 0) {
            return new ResponseEntity<>("No users available", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/single")
    public ResponseEntity<?> getSingleUser(Long id){

        // gets all users in the system.
        AdminUserRequest user = customUserDetailsService.getSingleUser(id);
        if (user == null) {
            return new ResponseEntity<>("No user available", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/approvals")
    public ResponseEntity<?> getApprovals(@RequestParam String role){
        if (UserRole.getUserRoleCode(role) != UserRole.ADMIN) {
            return new ResponseEntity<>("Must be admin", HttpStatus.BAD_REQUEST);
        }
        // gets all users in the system.
        List<AdminUserRequest> users = customUserDetailsService.getAllUsersPendingApproval();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
