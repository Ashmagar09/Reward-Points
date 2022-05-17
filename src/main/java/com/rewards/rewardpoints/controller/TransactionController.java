package com.rewards.rewardpoints.controller;

import com.rewards.rewardpoints.entity.Transaction;
import com.rewards.rewardpoints.exception.ValidationException;
import com.rewards.rewardpoints.model.TransactionDto;
import com.rewards.rewardpoints.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody TransactionDto transactionDto) throws ValidationException {
        return new ResponseEntity<>(transactionService.add(transactionDto), HttpStatus.OK);
    }

    @GetMapping("/mock-data")
    public ResponseEntity<List<Transaction>> mockData() {
        return new ResponseEntity<>(transactionService.mockTransactions(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> get(){
        return new ResponseEntity<>(transactionService.get(), HttpStatus.OK);
    }


}
