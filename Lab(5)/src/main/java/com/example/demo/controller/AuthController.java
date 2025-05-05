package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for authentication-related endpoints: login, register, logout, etc.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login endpoint - authenticates user and returns JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Attempting login for user: {}", loginRequest.getUsername());
        return authService.loginUser(loginRequest);
    }

    /**
     * Register endpoint - creates a new user account.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    /**
     * Check if user is authenticated (used for frontend token validation).
     */
    @GetMapping("/check-authentication")
    public ResponseEntity<String> checkAuthentication() {
        return ResponseEntity.ok("You are authenticated!");
    }

    /**
     * Logout endpoint (optional) - can be expanded to handle token revocation or session clearing.
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        return ResponseEntity.ok("You have logged out successfully!");
    }
}
