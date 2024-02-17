package com.sandeep.securityone.controller;

import com.sandeep.securityone.repositories.UserRepository;
import com.sandeep.securityone.requests.ChangePasswordRequest;
import com.sandeep.securityone.requests.UserUpdateRequest;
import com.sandeep.securityone.responses.UserResponse;
import com.sandeep.securityone.services.JwtService;
import com.sandeep.securityone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    public JwtService jwtService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserService userService;

    @GetMapping ("/profile")
    public ResponseEntity<UserResponse> getUserProfile() {
        UserResponse user = userService.getUser();
        return new ResponseEntity<UserResponse>(user, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUserProfile(@RequestBody UserUpdateRequest userUpdateRequest) {
        UserResponse userResponse = userService.updateUser(userUpdateRequest);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changeUserPassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        String response = userService.changePassword(changePasswordRequest);
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        String response = userService.deleteUser(id);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
