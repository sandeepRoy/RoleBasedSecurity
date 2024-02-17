package com.sandeep.securityone.controller;

import com.sandeep.securityone.entities.User;
import com.sandeep.securityone.requests.ChangePasswordRequest;
import com.sandeep.securityone.requests.UserUpdateRequest;
import com.sandeep.securityone.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;


@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    public AdminService adminService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = adminService.getAllUsers();
        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest userUpdateRequest) {
        System.out.println("32 - AdminController");
        User user = adminService.updateUserProfile(id, userUpdateRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<String> changePasswordofUser(@PathVariable Integer id, @RequestBody ChangePasswordRequest changePasswordRequest) {
        String response = adminService.changePasswordofUser(id, changePasswordRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdmin() {
        String response = adminService.deleteAdmin();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}