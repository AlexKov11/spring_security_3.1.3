package com.example.spring_313.service;



import com.example.spring_313.entities.Role;
import com.example.spring_313.entities.User;

import java.util.List;

public interface UserServ {
    List<User> getAllUsers();

    boolean saveUser(User user);

    void createRolesIfNotExist();

    boolean updateUser(User updateUser, List<Role> roles);

    User showUser(Long id);

    void deleteUser(Long id);

}
