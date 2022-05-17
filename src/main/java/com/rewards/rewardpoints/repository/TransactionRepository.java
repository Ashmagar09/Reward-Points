package com.rewards.rewardpoints.repository;

import com.rewards.rewardpoints.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerIdAndCreatedDateGreaterThanEqual(Long customerId, Date date);
}
