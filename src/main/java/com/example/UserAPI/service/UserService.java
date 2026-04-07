package com.example.UserAPI.service;

import com.example.UserAPI.model.User;
import com.example.UserAPI.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @GetMapping("/Users")
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public List<User> searchByName(String name) {
        return userRepository.findByNameContaining(name);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
