package com.rewards.rewardpoints.service;

import com.rewards.rewardpoints.exception.NoTransactionFoundException;
import com.rewards.rewardpoints.model.RewardsResponse;

public interface RewardsService {

    RewardsResponse calculateRewardPoints(Long customerId) throws NoTransactionFoundException;
}
