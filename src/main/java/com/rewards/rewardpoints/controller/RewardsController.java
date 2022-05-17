package com.rewards.rewardpoints.controller;

import com.rewards.rewardpoints.exception.NoTransactionFoundException;
import com.rewards.rewardpoints.model.RewardsResponse;
import com.rewards.rewardpoints.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/{customerId}")
    public ResponseEntity<RewardsResponse> calculateReward(@PathVariable("customerId") Long customerId) throws NoTransactionFoundException {
        return new ResponseEntity<>(rewardsService.calculateRewardPoints(customerId), HttpStatus.OK);
    }

}
