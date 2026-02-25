package com.life.privacyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.life.privacyapp.entity.User;
import com.life.privacyapp.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already registered!";
        }

        //user.setTotalXP(0);
        userRepository.save(user);

        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {

    User existingUser = userRepository.findByEmail(user.getEmail());

    if (existingUser == null) {
        return "User not found!";
    }

    if (!existingUser.getPassword().equals(user.getPassword())) {
        return "Invalid password!";
    }

    return "Login successful!";
 }
}