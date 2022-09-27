package com.practicum.fttech.loginservice.repos;


import com.practicum.fttech.loginservice.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILoginRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
