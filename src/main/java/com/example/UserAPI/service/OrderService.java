package com.example.UserAPI.service;

import com.example.UserAPI.dto.*;
import com.example.UserAPI.model.*;
import com.example.UserAPI.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, CartRepository cartRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public void createOrder(){
        //JWTからユーザー情報を取得
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        //カート内の商品を取得
        List<Cart> cartItems = cartRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("カートが空です");
        }

        //注文を作成
        Order order = new Order();
        order.setUser(user);
        order.setCreateAt(LocalDateTime.now());
        orderRepository.save(order);

        //注文アイテムを作成
        for (Cart cart : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cart.getProduct());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setPrice(cart.getProduct().getPrice());

            orderItemRepository.save(orderItem);
        }

        //カートを空にする
        cartRepository.deleteAll(cartItems);
    }

    public List<OrderResponse> getOrders(){
        //JWTからユーザー情報を取得
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

        //注文を取得
        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream()
                .map(order ->{
                    List<OrderItem> orderItems = orderItemRepository.findByOrder(order);

                    List<OrderItemResponse> orderItemResponses = orderItems.stream()
                            .map(item -> new OrderItemResponse(
                                    item.getProduct().getId(),
                                    item.getProduct().getName(),
                                    item.getPrice(),
                                    item.getQuantity()))
                            .collect(Collectors.toList());
                
                    double totalPrice = orderItems.stream()
                            .mapToInt(item -> item.getPrice().intValue() * item.getQuantity())
                            .sum();

                    return new OrderResponse(
                            order.getId(), 
                            order.getCreateAt(),
                            orderItemResponses,
                            totalPrice);
                })
                .collect(Collectors.toList());
    }
}
