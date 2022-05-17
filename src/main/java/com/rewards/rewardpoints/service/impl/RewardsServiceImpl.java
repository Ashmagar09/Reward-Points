package com.rewards.rewardpoints.service.impl;

import com.rewards.rewardpoints.entity.Transaction;
import com.rewards.rewardpoints.exception.NoTransactionFoundException;
import com.rewards.rewardpoints.model.RewardsResponse;
import com.rewards.rewardpoints.repository.TransactionRepository;
import com.rewards.rewardpoints.service.RewardsService;
import com.rewards.rewardpoints.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger logger = LoggerFactory.getLogger(RewardsServiceImpl.class);

    @Override
    public RewardsResponse calculateRewardPoints(Long customerId) throws NoTransactionFoundException {

        logger.debug("Fetching transactions for customer id {}",customerId);
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndCreatedDateGreaterThanEqual(customerId, DateUtil.get3MonthOldDate());
        if(transactions.isEmpty()){
            throw new NoTransactionFoundException("No Transaction is present for the customer ID "+customerId);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
        logger.debug("Calculating rewards for customer with id {}", customerId);
        Map<String, Double> reward = transactions.stream().collect(Collectors.toMap(transaction -> sdf.format(transaction.getCreatedDate()),
                        this::getRewardPoint,Double::sum));

        return RewardsResponse.builder()
                .customerId(customerId)
                .total(reward.values().stream().reduce(0.0,Double::sum))
                .rewardPoints(reward)
                .build();
    }

    private Double getRewardPoint(Transaction transaction) {
        Double price = transaction.getPrice();
        double rewards=0.0;
        if(price>50){
            double difference = price-50;
            rewards+=difference*1;
            if(difference>50) {
                rewards += (difference - 50) * 1;
            }
        }
        return rewards;
    }

}
