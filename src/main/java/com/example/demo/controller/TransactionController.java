package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TransactionController {

    private TransactionRepository transactionRepository;
    private HashMap<String, Object> map = new HashMap<>();

    @Autowired
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

        map.put("error", 0);
        map.put("message", "success retrieving data!");
        map.put("data", null);
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        transactionRepository.create(transaction);

        map.put("message", "Transaction created successfully");
        map.put("data", transaction);

        return ResponseEntity.ok(map);
    }

    @GetMapping("/transactions")
    public Map<String, Object> getTransactions(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) Integer userId) {

        map.put("data", transactionRepository.findAll(from, to, userId));

        return map;
    }
}