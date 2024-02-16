package com.sandeep.securityone.services;

import com.sandeep.securityone.responses.AuthResponse;
import com.sandeep.securityone.requests.AuthenticateRequest;
import com.sandeep.securityone.requests.RegisterRequest;
import com.sandeep.securityone.entities.Role;
import com.sandeep.securityone.entities.User;
import com.sandeep.securityone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public JwtService jwtService;

    @Autowired
    public AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User
                .builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse authenticate(AuthenticateRequest authenticateRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(), authenticateRequest.getPassword())
        );
        User user = userRepository.findUserByEmail(authenticateRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return AuthResponse.builder().token(token).build();
    }
}
