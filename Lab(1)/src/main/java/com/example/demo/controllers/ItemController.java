package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @GetMapping
    public String getResource() {
        return "GET request received";
    }

    @PostMapping
    public String createResource() {
        return "POST request received";
    }

    @PutMapping
    public String updateResource() {
        return "PUT request received";
    }

    @DeleteMapping
    public String deleteResource() {
        return "DELETE request received";
    }
}
