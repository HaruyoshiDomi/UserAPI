package com.example.UserAPI.dto;

import lombok.Getter;

@Getter
public class CartRequest {
    private Long productId;
    private int quantity;
}
