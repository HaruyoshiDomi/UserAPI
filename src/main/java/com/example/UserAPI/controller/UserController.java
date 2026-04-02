package com.example.UserAPI.controller;

import com.example.UserAPI.dto.UserRequest;
import com.example.UserAPI.model.User;
import com.example.UserAPI.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.create(userRequest.getName(), userRequest.getEmail());
    }

    @GetMapping
    public List<User> getUserList() {
        return userService.getUserList();
    }

}
