package com.example.demo.controller;

import java.util.HashMap;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private HashMap<String, Object> map = new HashMap<>();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

        map.put("error", 0);
        map.put("message", "success retrieving data!");
        map.put("data", null);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        map.put("data", userService.findAll());

        return ResponseEntity.ok(map);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getName().length() <= 3) {
            map.put("error", 1);
            map.put("message", "Username must be at least 3 characters.");
            map.put("data", user);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        userService.create(user);

        map.put("message", "User created successfully");
        map.put("data", user);

        return ResponseEntity.ok(map);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        map.put("data", userService.findById(id));

        return ResponseEntity.ok(map);
    }

}