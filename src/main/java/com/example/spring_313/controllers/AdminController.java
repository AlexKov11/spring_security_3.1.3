package com.example.spring_313.controllers;

import com.example.spring_313.entities.User;
import com.example.spring_313.repositories.RoleRepo;
import com.example.spring_313.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleRepo roleRepo;

    public AdminController(UserService userService, RoleRepo roleRepo) {
        this.userService = userService;
        this.roleRepo = roleRepo;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleRepo.findAll());
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("allRoles", roleRepo.findAll());
        return "edit";
    }

    @PostMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user, new ArrayList<>(user.getRoles()));
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}