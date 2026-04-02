package com.example.UserAPI.service;

import com.example.UserAPI.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public User create(String name, String email){
        User user = new User();
        user.setId(idGenerator.getAndIncrement());
        user.setName(name);
        user.setEmail(email);

        users.add(user);
        return user;
    }

    public List<User> getUserList() {
        return users;
    }
}
