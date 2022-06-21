package com.example.mailauthstudy.repository;

import com.example.mailauthstudy.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, String> {
    Optional<EmailAuth> findByEmailAndAuthTokenAndExpireDateAfterAndExpired(String email, String authToken, LocalDateTime now, Boolean expired);
}
