package com.example.tchedule.controller;

import com.example.tchedule.model.User;
import com.example.tchedule.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() { return userService.getAllUsers();}

    @PostMapping
    public User addUser(User users){ return userService.addUser(users);}


}
