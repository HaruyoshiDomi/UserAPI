package com.example.UserAPI.repository;

import com.example.UserAPI.model.Cart;
import com.example.UserAPI.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
    List<Cart> findByUser(User user);
    void deleteByUser(User user);
    Optional<Cart> findByUserAndProductId(User user, Long productId);
}
