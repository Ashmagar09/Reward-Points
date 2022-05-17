package com.rewards.rewardpoints.model;

import lombok.Data;

@Data
public class TransactionDto {
    private Long customerId;
    private String productName;
    private Double price;
}
