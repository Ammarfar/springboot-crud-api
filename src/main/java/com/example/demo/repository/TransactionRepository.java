package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Transaction;

public interface TransactionRepository {
    public List<Transaction> findAll(String from, String to, Integer userId);

    public void create(Transaction transaction);
}
