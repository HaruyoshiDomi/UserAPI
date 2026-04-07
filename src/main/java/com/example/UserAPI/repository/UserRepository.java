package com.example.UserAPI.repository;

import com.example.UserAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String name);
    User findByEmail(String email);
}
