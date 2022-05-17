package com.rewards.rewardpoints.service.impl;

import com.rewards.rewardpoints.entity.Transaction;
import com.rewards.rewardpoints.exception.ValidationException;
import com.rewards.rewardpoints.model.TransactionDto;
import com.rewards.rewardpoints.repository.TransactionRepository;
import com.rewards.rewardpoints.service.TransactionService;
import com.rewards.rewardpoints.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionServiceImpl implements TransactionService {

    public static final String TEST_PRODUCT = "Test Product";
    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Override
    @PostConstruct
    public List<Transaction> mockTransactions() {
        logger.info("Mocking the transactions data");
        List<Transaction> transactions = Stream.of(40.0, 120.0, 70.0, 20.0, 60.0, 130.0).map(price -> Transaction.builder()
                .price(price)
                .createdDate(DateUtil.getDate())
                .customerId(1L)
                .productName(TEST_PRODUCT)
                .build()).collect(Collectors.toList());
        return transactionRepository.saveAll(transactions);
    }

    @Override
    public Transaction add(TransactionDto transactionDto) throws ValidationException {
        if(transactionDto.getCustomerId()==null){
            logger.debug("Provided transaction does not have a customer id");
            throw new ValidationException("Please provide a customer Id");
        }
        if(transactionDto.getPrice()==null || transactionDto.getPrice()==0){
            logger.debug("Provided transaction does not have the price");
            throw new ValidationException("Please provide price of the product");
        }
        Transaction transaction= Transaction.builder()
                .customerId(transactionDto.getCustomerId())
                .productName(transactionDto.getProductName())
                .price(transactionDto.getPrice())
                .productName(transactionDto.getProductName())
                .createdDate(new Date())
                .build();
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> get() {
        logger.debug("Fetching all the transactions present in the table, for verifying the result");
        return transactionRepository.findAll();
    }

}
