package com.example.UserAPI.controller;

import com.example.UserAPI.dto.CartRequest;
import com.example.UserAPI.dto.CartResponse;
import com.example.UserAPI.service.CartService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody CartRequest cartRequest, Authentication authentication) {

        String email = authentication.getName();
        cartService.addToCart(cartRequest, email);
    }

    @GetMapping
    public List<CartResponse> getCartList(Authentication authentication) {
        String email = authentication.getName();
        return cartService.getCartList(email);
    }
}
