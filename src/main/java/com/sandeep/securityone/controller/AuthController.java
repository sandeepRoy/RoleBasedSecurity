package com.sandeep.securityone.controller;

import com.sandeep.securityone.responses.AuthResponse;
import com.sandeep.securityone.requests.AuthenticateRequest;
import com.sandeep.securityone.services.UserAuthenticationService;
import com.sandeep.securityone.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    public UserAuthenticationService userAuthenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userAuthenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthenticateRequest authenticateRequest) {
        return ResponseEntity.ok(userAuthenticationService.authenticate(authenticateRequest));
    }
}
