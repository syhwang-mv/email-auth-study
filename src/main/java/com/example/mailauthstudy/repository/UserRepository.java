package com.example.mailauthstudy.repository;

import com.example.mailauthstudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAddress(String emailAddress);
    Optional<User> findByLoginIdAndPassword(String loginId, String password);
}
