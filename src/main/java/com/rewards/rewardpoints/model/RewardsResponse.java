package com.rewards.rewardpoints.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardsResponse {

    private Long customerId;
    private Map<String,Double> rewardPoints;
    private Double total;

}
