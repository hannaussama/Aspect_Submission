package com.example.Lab.Aspect3.controller;

import com.example.Lab.Aspect3.dto.CreateTransactionDTO;
import com.example.Lab.Aspect3.dto.UpdateTransactionDTO;
import com.example.Lab.Aspect3.entity.Transaction;
import com.example.Lab.Aspect3.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody CreateTransactionDTO dto) {
        Transaction created = transactionService.createTransaction(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable int id,
                                                         @Valid @RequestBody UpdateTransactionDTO dto) {
        Transaction updated = transactionService.updateTransaction(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }
}