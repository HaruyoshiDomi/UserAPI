package com.example.UserAPI.controller;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserAPI.dto.LoginRequest;
import com.example.UserAPI.service.UserService;
import com.example.UserAPI.model.User;
import com.example.UserAPI.security.JwtUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        //DBからユーザーを取得
        User user = userService.findByEmail(request.getEmail());

        //ユーザーが存在しない場合はエラー
        if(user == null) {
            throw new RuntimeException("User not found");
        }

        //パスワードが一致しない場合はエラー
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        String token = JwtUtil.generateToken(user.getEmail());
        //ログイン成功(JWT発行)
        return Map.of("token",token);
    }
    
}
