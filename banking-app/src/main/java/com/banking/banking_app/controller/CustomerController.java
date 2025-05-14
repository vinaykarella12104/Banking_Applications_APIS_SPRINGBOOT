package com.banking.banking_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_app.dto.DepositRequest;
import com.banking.banking_app.dto.TransferRequest;
import com.banking.banking_app.dto.WithdrawRequest;
import com.banking.banking_app.model.Transaction;
import com.banking.banking_app.service.TransactionService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private TransactionService transactionService;

    /** 	 Deposit Money */
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest request) {
        return transactionService.deposit(request.getAccountId(), request.getAmount());
    }

    /**  Withdraw Money */
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawRequest request) {
        return transactionService.withdraw(request.getAccountId(), request.getAmount());
    }

    /**  Transfer Money */
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        return transactionService.transfer(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
    }

    /**  Get Transactions */
    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactions(accountId));
    }
}
