package com.example.Lab.Aspect3.repo;
import com.example.Lab.Aspect3.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}