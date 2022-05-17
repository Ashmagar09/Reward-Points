package com.rewards.rewardpoints.service;

import com.rewards.rewardpoints.entity.Transaction;
import com.rewards.rewardpoints.exception.ValidationException;
import com.rewards.rewardpoints.model.TransactionDto;

import java.util.List;

public interface TransactionService {

    List<Transaction> mockTransactions();

    Transaction add(TransactionDto transactionDto) throws ValidationException;

    List<Transaction> get();

}
