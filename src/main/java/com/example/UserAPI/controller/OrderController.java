package com.example.UserAPI.controller;

import com.example.UserAPI.service.OrderService;
import com.example.UserAPI.dto.OrderResponse;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void createOrder() {
        orderService.createOrder();
    }

    @GetMapping
    public List<OrderResponse> getOrders() {
        return orderService.getOrders();
    }
    
}
