package com.example.Lab.Aspect3.service;

import com.example.Lab.Aspect3.entity.Transaction;
import com.example.Lab.Aspect3.entity.User;
import com.example.Lab.Aspect3.repo.TransactionRepo;
import com.example.Lab.Aspect3.repo.UserRepo; // Import the UserRepo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List; // Add the import for List
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;  // Spring should inject this if UserRepo is a valid repository
    @Autowired
    private TransactionRepo transactionRepository;
    // Method to create a user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Method to get a user by their ID
    public User getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Method to delete a user by their ID
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Spring Data will return a List of users
    }

    // Method to update a user
    public User updateUser(User user) {
        return userRepository.save(user); // Save or update the user
    }
    public User trnsToUser(Integer transactionId, Integer userId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getUserTransactions().add(transaction); // Add user to event
        user.setBalance(user.getBalance() + transaction.getAmount());
        return userRepository.save(user);
    }
}
