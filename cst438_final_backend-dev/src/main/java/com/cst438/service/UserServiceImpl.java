package com.cst438.service;

import com.cst438.domain.User1;
import com.cst438.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User1 registerUser(User1 user) {
        // Validate that both username and password are provided
        if (user.getAlias() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }
       
        // Check if the username is already taken
        if (userRepository.findByAlias(user.getAlias()) != null) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        // Save 
        return userRepository.save(user);
    }
}
