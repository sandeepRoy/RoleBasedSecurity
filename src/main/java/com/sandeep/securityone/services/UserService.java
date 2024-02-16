package com.sandeep.securityone.services;

import com.sandeep.securityone.entities.User;
import com.sandeep.securityone.repositories.UserRepository;
import com.sandeep.securityone.requests.UserUpdateRequest;
import com.sandeep.securityone.requests.ChangePasswordRequest;
import com.sandeep.securityone.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public JwtService jwtService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public UserResponse getUser() {
        String username = jwtService.extractUsername(JwtService.token);
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        UserResponse response = UserResponse
                .builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .id(user.getId())
                .phonenumber(user.getPhonenumber())
                .city(user.getCity())
                .username(user.getUsername())
                .build();

        return response;
    }

    public UserResponse updateUser(UserUpdateRequest userUpdateRequest) {
        String token = JwtService.token;
        String username = jwtService.extractUsername(token);

        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        user.setFirstname(userUpdateRequest.getFirstname());
        user.setLastname(userUpdateRequest.getLastname());
        user.setEmail(userUpdateRequest.getEmail());
        user.setCity(userUpdateRequest.getCity());
        user.setPhonenumber(userUpdateRequest.getPhonenumber());

        userRepository.save(user);

        UserResponse response = UserResponse
                .builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .id(user.getId())
                .phonenumber(user.getPhonenumber())
                .city(user.getCity())
                .username(user.getUsername())
                .build();

        return response;
    }

    public String changePassword(ChangePasswordRequest changePasswordRequest) {
        String token = JwtService.token;
        String username = jwtService.extractUsername(token);

        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNew_password()));

        userRepository.save(user);

        return "Password Changed";
    }
}
