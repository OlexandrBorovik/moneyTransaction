package com.example.moneytransactions.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;

@Repository
public class BalanceRepository {
    private final HashMap<Long, BigDecimal> storage = new HashMap<>();

    public BigDecimal getBalanceForId(Long accountId) {
        return  storage.get(accountId);
    }

    public void save(Long id, BigDecimal amount) {

    }
}
