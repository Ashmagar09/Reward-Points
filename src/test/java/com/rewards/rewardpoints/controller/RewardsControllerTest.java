package com.rewards.rewardpoints.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewards.rewardpoints.entity.Transaction;
import com.rewards.rewardpoints.model.RewardsResponse;
import com.rewards.rewardpoints.repository.TransactionRepository;
import com.rewards.rewardpoints.service.RewardsService;
import com.rewards.rewardpoints.service.TransactionService;
import com.rewards.rewardpoints.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RewardsService rewardsService;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private TransactionService transactionService;

    @Test
    void givenValidCustomerId_whenTriggered_thenReturnRewards() throws Exception {
        when(transactionRepository.findByCustomerIdAndCreatedDateGreaterThanEqual(any(),any()))
                .thenReturn(mockTransactions());
        MvcResult result = this.mockMvc.perform(get("/api/v1/rewards/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        RewardsResponse response = new ObjectMapper().readValue(result.getResponse().getContentAsString(),RewardsResponse.class);
        Assertions.assertEquals(230.0,response.getTotal());
    }

    @Test
    void givenInvalidCustomerId_whenTriggered_thenReturnError() throws Exception {
        when(transactionRepository.findByCustomerIdAndCreatedDateGreaterThanEqual(any(),any()))
                .thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/api/v1/rewards/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    public List<Transaction> mockTransactions() {
        return Stream.of(40.0, 120.0, 70.0, 20.0, 60.0, 130.0).map(price -> Transaction.builder()
                .price(price)
                .createdDate(DateUtil.getDate())
                .customerId(1L)
                .build()).collect(Collectors.toList());

    }

}
