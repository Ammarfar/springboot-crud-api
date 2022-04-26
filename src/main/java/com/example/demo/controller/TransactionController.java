package com.example.demo.controller;


import com.example.demo.helper.Response;
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
    private Response response = new Response();

    @Autowired
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {

        response.setMessage("Transaction created successfully");
        response.setData(transaction);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactions(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) Integer userId) {

        response.successRetrieving();
        response.setData(transactionRepository.findAll(from, to, userId));

        return ResponseEntity.ok(response);
    }
}