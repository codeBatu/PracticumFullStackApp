package com.practicum.fttech.accountservice.repository;


import com.practicum.fttech.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccRepository extends JpaRepository<Account, Long> {
}


