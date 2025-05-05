package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Sample controller for testing public and protected API access.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class HomeController {

    /**
     * Public endpoint - accessible without authentication.
     */
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    /**
     * Protected endpoint - requires the user to be authenticated.
     */
    @GetMapping("/hello")
    @PreAuthorize("isAuthenticated()")
    public String hello() {
        return "Hello from protected endpoint!";
    }
}
