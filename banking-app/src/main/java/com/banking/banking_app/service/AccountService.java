package com.banking.banking_app.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banking.banking_app.model.Account;
import com.banking.banking_app.model.User;
import com.banking.banking_app.repository.AccountRepository;
import com.banking.banking_app.repository.UserRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> createAccount(Long userId, String accountType, double initialBalance) {
        //  Find user by ID
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        User user = userOptional.get();

        // Generate Unique Account Number
        String accountNumber = generateAccountNumber();

        //  Create Account
        Account account = new Account();
        account.setUser(user);
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(initialBalance);

        accountRepository.save(account);

        return ResponseEntity.ok("Account created successfully with Account Number: " + accountNumber);
    }

    //  Method to generate a unique 10-digit account number
    private String generateAccountNumber() {
        Random random = new Random();
        String accountNumber;
        do {
            accountNumber = String.valueOf(1000000000L + random.nextInt(900000000));
        } while (accountRepository.findByAccountNumber(accountNumber).isPresent());

        return accountNumber;
    }
}
