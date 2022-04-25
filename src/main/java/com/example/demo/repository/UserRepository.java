package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.User;

public interface UserRepository {
    public List<User> findAll();

    public User findById(int id);

    public void create(User user);
}
