package com.example.UserAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private LocalDateTime createAt;
    private List<OrderItemResponse> orderItems;
    private Double totalPrice;
}
