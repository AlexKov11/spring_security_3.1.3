package com.example.spring_313.controllers;


import com.example.spring_313.entities.User;
import com.example.spring_313.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        model.addAttribute("user", user);
        return "showuser";
    }
}
