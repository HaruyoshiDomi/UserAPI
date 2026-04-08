package com.example.UserAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartResponse {
    
    private Long produckId;
    private String productName;
    private Double price;
    private int quantity;
}
