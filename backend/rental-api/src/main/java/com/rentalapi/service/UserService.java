
package com.rentalapi.service;

import com.rentalapi.entity.User;
import com.rentalapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(
            String fullName,
            String username,
            String email,
            String mobile,
            String password
    ) {

        // Check username
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        // Check email
        if (email != null && !email.isBlank()
                && userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }

        // Check mobile
        if (mobile != null && !mobile.isBlank()
                && userRepository.existsByMobile(mobile)) {
            throw new RuntimeException("Mobile already registered");
        }

        // At least one contact method is required
        if ((email == null || email.isBlank())
                && (mobile == null || mobile.isBlank())) {
            throw new RuntimeException("Email or mobile is required");
        }

        // Create user
        User user = new User();

        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setMobile(mobile);

        // Hash password
        user.setPasswordHash(passwordEncoder.encode(password));

        // Save user
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}
