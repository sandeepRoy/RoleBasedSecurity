package com.sandeep.securityone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
public class ManagementController {
    @GetMapping("/get")
    public ResponseEntity<String> getData() {
        return new ResponseEntity<>("GET : Management", HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<String> postData() {
        return new ResponseEntity<>("POST : Management", HttpStatus.CREATED);
    }

    @PutMapping("/put")
    public ResponseEntity<String> putData() {
        return new ResponseEntity<>("PUT : Management", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteData() {
        return new ResponseEntity<>("DELETE : Management", HttpStatus.OK);
    }
}
