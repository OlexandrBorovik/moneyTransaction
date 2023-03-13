package com.example.moneytransactions.controller;

import com.example.moneytransactions.model.TransferBalance;
import com.example.moneytransactions.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController("/balance")
@AllArgsConstructor
public class BalanceController {

    private  final BankService bankService;

    @GetMapping("/{accountId}")
public BigDecimal getBalance(@PathVariable Long accountId){

        return bankService.getBalance(accountId);
    }

    @PostMapping("/add")
    public BigDecimal addMoney(@RequestBody TransferBalance amount){

        return bankService.addMoney(amount.getTo(),amount.getAmount());
    }
    @PostMapping("/transfer")
    public void transfer (@RequestBody TransferBalance transferBalance){

     bankService.makeTransfer (transferBalance );
    }
}
