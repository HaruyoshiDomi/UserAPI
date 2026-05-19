package com.example.UserAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.UserAPI.dto.LoginFrom;

@Controller
public class LoginPageController {
    
    @GetMapping("/login-page")
    public String showLoginPage() {
        return "login"; // This will return the login.html template
    }

}
