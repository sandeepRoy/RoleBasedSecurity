package com.sandeep.securityone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/get")
    public ResponseEntity<String> getAdmin() {
        return new ResponseEntity<>("GET : Admin", HttpStatus.OK);
    }
    
    @PostMapping("/post")
    public ResponseEntity<String> postAdmin() {
        return new ResponseEntity<>("POST: Admin", HttpStatus.CREATED);
    }

    @PutMapping("/put")
    public ResponseEntity<String> putAdmin() {
        return new ResponseEntity<String>("PUT: Admin", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdmin() {
        return new ResponseEntity<String>("DELETE: Admin", HttpStatus.CREATED);
    }
}