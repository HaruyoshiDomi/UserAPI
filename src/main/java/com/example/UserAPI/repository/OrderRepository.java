package com.example.UserAPI.repository;

import com.example.UserAPI.model.Order;
import com.example.UserAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
