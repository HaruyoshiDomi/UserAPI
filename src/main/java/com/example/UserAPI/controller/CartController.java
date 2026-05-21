package com.example.UserAPI.controller;

import com.example.UserAPI.dto.CartRequest;
import com.example.UserAPI.dto.CartResponse;
import com.example.UserAPI.service.CartService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


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

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
    }

    @PutMapping("/{id}/increase")
    public void increaseQuantity(@PathVariable Long id) {
        cartService.increaseQuantity(id);
    }

    @PutMapping("/{id}/decrease")
    public void decreaseQuantity(@PathVariable Long id) {
        cartService.decreaseQuantity(id);
    }

}
