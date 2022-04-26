package com.example.demo.controller;

import com.example.demo.helper.Response;
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
    private Response response = new Response();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        response.successRetrieving();
        response.setData(userService.findAll());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getName().length() <= 3) {
            response.error();
            response.setMessage("Username must be at least 3 characters.");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        userService.create(user);

        response.setMessage("User created successfully");
        response.setData(user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        response.successRetrieving();
        response.setData(userService.findById(id));

        return ResponseEntity.ok(response);
    }

}