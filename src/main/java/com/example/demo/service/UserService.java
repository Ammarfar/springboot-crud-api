package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
    public List<User> findAll();

    public User findById(int id);

    public void create(User user);
}
