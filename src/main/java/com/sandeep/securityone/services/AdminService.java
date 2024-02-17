package com.sandeep.securityone.services;

import com.sandeep.securityone.entities.Role;
import com.sandeep.securityone.entities.User;
import com.sandeep.securityone.repositories.UserRepository;
import com.sandeep.securityone.requests.ChangePasswordRequest;
import com.sandeep.securityone.requests.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.PasswordView;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public JwtService jwtService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        List<User> all = userRepository.findAll();

        for(User u : all) {
            if(u.getRole().equals(Role.USER)){
                users.add(u);
            }
        }
        return users;
    }

    public User updateUserProfile(Integer id, UserUpdateRequest userUpdateRequest) {
        System.out.println("36 : AdminService ");
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
        System.out.println("37 : AdminService " + user.getUsername());
        user.setFirstname(userUpdateRequest.getFirstname());
        user.setLastname(userUpdateRequest.getLastname());
        user.setEmail(userUpdateRequest.getEmail());
        user.setCity(userUpdateRequest.getCity());
        user.setPhonenumber(userUpdateRequest.getPhonenumber());

        userRepository.save(user);

        return user;
    }

    public String changePasswordofUser(Integer id, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setEmail(user.getEmail());
        user.setCity(user.getCity());
        user.setPhonenumber(user.getPhonenumber());
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNew_password()));

        userRepository.save(user);
        return "Password Changed";
    }

    public String deleteAdmin() {
        String username = jwtService.extractUsername(JwtService.token);
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
        if(user.getRole().equals("ADMIN")){
            userRepository.delete(user);
            return "Deleted";
        }
        else{
            return "Can't Delete, User Not Found!";
        }
    }
}
