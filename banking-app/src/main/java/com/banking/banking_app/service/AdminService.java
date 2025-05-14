package com.banking.banking_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.banking_app.model.Account;
import com.banking.banking_app.model.Transaction;
import com.banking.banking_app.model.User;
import com.banking.banking_app.repository.AccountRepository;
import com.banking.banking_app.repository.TransactionRepository;
import com.banking.banking_app.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
