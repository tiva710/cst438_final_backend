package com.cst438.service;

import com.cst438.domain.User;
import com.cst438.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicelmpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        // Validate that both username and password are provided
        if (user.getUserName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        // Check if the username is already taken
        if (userRepository.findByusername(user.getUserName()) != null) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        // Save 
        return userRepository.save(user);
    }
}