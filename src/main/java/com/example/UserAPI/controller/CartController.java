package com.example.UserAPI.controller;

import com.example.UserAPI.dto.CartRequest;
import com.example.UserAPI.dto.CartResponse;
import com.example.UserAPI.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public void addToCart(@RequestBody CartRequest cartRequest) {
        cartService.addToCart(cartRequest);
    }

    @GetMapping
    public List<CartResponse> getCartList() {
        return cartService.getCartList();
    }
}
