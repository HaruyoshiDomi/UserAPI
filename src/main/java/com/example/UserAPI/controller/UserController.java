package com.example.UserAPI.controller;

import com.example.UserAPI.dto.UserRequest;
import com.example.UserAPI.dto.UserResponse;
import com.example.UserAPI.model.User;
import com.example.UserAPI.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.create(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        return new UserResponse(
                user.getId(), 
                user.getName(),
                user.getEmail());
    }

        @GetMapping
    public List<UserResponse> findAll() {
        return userService.getUserList().stream()
                .map(user -> new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail()))
                .collect(Collectors.toList());
    }


    @GetMapping("/search")
    public List<UserResponse> searchUsers(@RequestParam String name) {
        return userService.searchByName(name).stream()
                .map(user -> new UserResponse(
                    user.getId(), 
                    user.getName(), 
                    user.getEmail()))
                .collect(Collectors.toList());
    }

}
