package com.example.UserAPI.repository;

import com.example.UserAPI.model.OrderItem;
import com.example.UserAPI.model.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);    
}
