package com.example.moneytransactions.service;

import com.example.moneytransactions.model.TransferBalance;
import com.example.moneytransactions.repository.BalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BankService {
    private final BalanceRepository balanceRepository;
    public BigDecimal getBalance(Long accountId) {
        BigDecimal balance = balanceRepository.getBalanceForId(accountId);
        if (balance == null){
            return  balance.add(BigDecimal.ZERO);
        }
        return balance;
    }

    public BigDecimal addMoney(Long to, BigDecimal amount) {
        BigDecimal currentBalance = balanceRepository.getBalanceForId(to);
        if (currentBalance == null) {
            balanceRepository.save(to, amount);
            return amount;
        } else {
            BigDecimal updateBalance = currentBalance.add(amount);
            balanceRepository.save(to, updateBalance);
            return updateBalance;
        }
    }
    public void makeTransfer(TransferBalance transferBalance) {
        BigDecimal fromBalance = balanceRepository.getBalanceForId(transferBalance.getFrom());
        BigDecimal toBalance = balanceRepository.getBalanceForId(transferBalance.getTo());
        if(fromBalance==null || toBalance ==null) throw new IllegalArgumentException();
        if(transferBalance.getAmount().compareTo(fromBalance)>0) throw new IllegalArgumentException("no money");
       BigDecimal updateFrom =fromBalance.subtract(transferBalance.getAmount());
        BigDecimal updateTo = toBalance.add(transferBalance.getAmount());
balanceRepository.save(transferBalance.getFrom(),updateFrom);
        balanceRepository.save(transferBalance.getTo(),updateTo);
    }
}
