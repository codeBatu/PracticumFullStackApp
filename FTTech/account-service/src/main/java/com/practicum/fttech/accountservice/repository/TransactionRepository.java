package com.practicum.fttech.accountservice.repository;


import com.practicum.fttech.accountservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

