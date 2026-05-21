package com.example.UserAPI.service;

import com.example.UserAPI.dto.CartRequest;
import com.example.UserAPI.dto.CartResponse;
import com.example.UserAPI.model.*;
import com.example.UserAPI.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    
    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    //カートに商品を追加
    public void addToCart(CartRequest cartRequest, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        //商品情報を取得
        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("商品が存在しません"));


        Optional<Cart> existingCartItem = cartRepository.findByUserAndProductId(user, cartRequest.getProductId());

        if (existingCartItem.isPresent()) {
            // 既にカートに商品が存在する場合、数量を更新
            Cart cart = existingCartItem.get();
            cart.setQuantity(cart.getQuantity() + cartRequest.getQuantity());
            cartRepository.save(cart);
        } else {
            // カートに商品を追加
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(cartRequest.getQuantity());
            cartRepository.save(cart);
        }
    }

    //カート内の商品を取得
    public List<CartResponse> getCartList(String email) {
        //JWTからユーザー情報を取得
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        //カート内の商品を取得
        List<Cart> cartItems = cartRepository.findByUser(user);
        return cartItems.stream()
                .map(cart -> new CartResponse(
                        cart.getId(),
                        cart.getProduct().getId(),
                        cart.getProduct().getName(),
                        cart.getProduct().getPrice(),
                        cart.getQuantity()))
                .collect(Collectors.toList());
    }

    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void increaseQuantity(Long cartId) {
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new RuntimeException("カートアイテムが存在しません"));
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
    }

    public void decreaseQuantity(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("カートアイテムが存在しません"));
        if (cart.getQuantity() > 1) {
            cart.setQuantity(cart.getQuantity() - 1);
        } else {
            cartRepository.deleteById(cartId);
        }
        cartRepository.save(cart);
    }
}