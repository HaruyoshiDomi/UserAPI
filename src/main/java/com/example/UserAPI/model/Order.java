package com.example.UserAPI.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders") //SQL予約語を避けるためテーブル名を指定
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ユーザーと注文の多対一の関係
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createAt;
}
