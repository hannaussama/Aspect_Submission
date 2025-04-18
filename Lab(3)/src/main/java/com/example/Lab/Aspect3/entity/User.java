package com.example.Lab.Aspect3.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Changed from Long to int
    private String email;
    private String password;
    private String phoneNumber;
    private double balance;
    @OneToMany(mappedBy = "sender")
    private List<Transaction> userTransactions;
    // Default constructor
    public User() {
    }

    // Constructor with 'id'
    public User(int id) {
        this.id = id;  // Updated to use int for the id
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {  // Changed from Long to int
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getUserTransactions() {
        return userTransactions;
    }
}