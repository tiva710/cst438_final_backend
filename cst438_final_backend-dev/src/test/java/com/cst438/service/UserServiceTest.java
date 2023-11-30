package com.cst438.service;

import com.cst438.domain.User1;
import com.cst438.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testRegisterUser() {
        // Clean up the database 
        userRepository.deleteAll();

        User1 user = new User1();
        user.setAlias("testUser");
        user.setPassword("testPassword");
        user.setRole("USER");

        // Save the user 
        User1 registeredUser = userService.registerUser(user);

        // Retrieve the user from the database and assert its values
        User1 retrievedUser = userRepository.findByAlias("testUser");
        assertEquals("testUser", retrievedUser.getAlias());
        assertEquals("testPassword", retrievedUser.getPassword());
        assertEquals("USER", retrievedUser.getRole());
    }

    @Test
    void testRegisterUserWithNullValues() {
        // register a user with null values
        User1 user = new User1();
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(user));
    }

    @Test
    void testRegisterUserWithDuplicateUsername() {
        
        userRepository.deleteAll();

        // Save a user with a specific username
        User1 existingUser = new User1();
        existingUser.setAlias("existingUser");
        existingUser.setPassword("password");
        existingUser.setRole("USER");
        userRepository.save(existingUser);

        // user with the same username
        User1 user = new User1();
        user.setAlias("existingUser");
        user.setPassword("newPassword");
        user.setRole("USER");

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(user));
    }
}