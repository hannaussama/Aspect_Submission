package com.example.Lab.Aspect3.controller;

import com.example.Lab.Aspect3.entity.User;
import com.example.Lab.Aspect3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST method to create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // GET method to retrieve a user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    // GET method to retrieve all userss
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // DELETE method to delete a user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    // PUT method to update a user by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        // Set the user ID before passing it to the service layer for updating
        user.setId(id);  // Ensure the ID from the path is set in the user object
        return userService.updateUser(user); // Call the service to handle the update
    }

    // POST method to handle transactions for a user
    @PostMapping("/{id}/trns")
    public ResponseEntity<User> trnsToUser(
            @PathVariable Integer id,           // Use the 'id' as the user identifier
            @RequestParam Integer transactionId) {   // Expecting 'transactionId' as a parameter
        // Handle the transaction logic
        User updated = userService.trnsToUser(transactionId, id);
        return ResponseEntity.ok(updated);
    }

}
