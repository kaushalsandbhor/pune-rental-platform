
package com.rentalapi.controller;

import com.rentalapi.dto.request.LoginRequest;
import com.rentalapi.dto.request.RegisterRequest;
import com.rentalapi.entity.User;
import com.rentalapi.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import com.rentalapi.dto.response.UserResponse;


import java.util.Map;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public AuthController(
            UserService userService,
            AuthenticationManager authenticationManager,
            SecurityContextRepository securityContextRepository) {

        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }


    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userService.getUserByUsername(username);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse) {

        try {

            // 1. Verify credentials
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getIdentifier(),
                                    request.getPassword()
                            )
                    );

            // 2. Create security context
            SecurityContext context =
                    SecurityContextHolder.createEmptyContext();

            context.setAuthentication(authentication);

            // 3. Set authenticated user
            SecurityContextHolder.setContext(context);

            // 4. Save authentication in HTTP session
            securityContextRepository.saveContext(
                    context,
                    httpRequest,
                    httpResponse
            );

            // 5. Return response
            return ResponseEntity.ok(
                    Map.of(
                            "message", "Login successful",
                            "username", authentication.getName()
                    )
            );

        } catch (AuthenticationException exception) {

            SecurityContextHolder.clearContext();

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "message", "Invalid credentials or account not activated"
                    ));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = userService.registerUser(
                request.getFullName(),
                request.getUsername(),
                request.getEmail(),
                request.getMobile(),
                request.getPassword()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "Registration successful",
                        "userId", user.getId(),
                        "username", user.getUsername(),
                        "accountStatus", user.getAccountStatus()
                ));
    }
}
