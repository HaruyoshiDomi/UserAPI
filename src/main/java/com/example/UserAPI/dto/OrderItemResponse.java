package com.example.UserAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemResponse {
    
    private Long productId;
    private String productName;
    private Double price;
    private int quantity;
}
