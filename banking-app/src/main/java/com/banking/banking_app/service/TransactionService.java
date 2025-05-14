package com.banking.banking_app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banking.banking_app.model.Account;
import com.banking.banking_app.model.Transaction;
import com.banking.banking_app.repository.AccountRepository;
import com.banking.banking_app.repository.TransactionRepository;

@Service 
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**  Deposit Money */
    public ResponseEntity<String> deposit(Long accountId, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        
        if (!optionalAccount.isPresent()) {
            return ResponseEntity.badRequest().body("Account not found with ID: " + accountId);
        }

        Account account = optionalAccount.get();
        account.setBalance(account.getBalance() + amount); //  Update balance

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSIT");
        transaction.setTimestamp(new Date());  //  Set current timestamp

        //  Validate that account exists before saving
        if (transaction.getAccount() == null) {
            return ResponseEntity.badRequest().body("Transaction does not have an associated account!");
        }

        //  Save updated account and transaction
        accountRepository.save(account);
        transactionRepository.save(transaction);
        
        return ResponseEntity.ok("Deposit successful! New balance: " + account.getBalance());
    }

    /**  Withdraw Money */
    public ResponseEntity<String> withdraw(Long accountId, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        
        if (!optionalAccount.isPresent()) {
            return ResponseEntity.badRequest().body("Account not found with ID: " + accountId);
        }

        Account account = optionalAccount.get();
        
        if (account.getBalance() < amount) {
            return ResponseEntity.badRequest().body("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("WITHDRAW");
        transaction.setTimestamp(new Date()); // ✅ Fix timestamp

        accountRepository.save(account);
        transactionRepository.save(transaction);
        
        return ResponseEntity.ok("Withdrawal successful! New balance: " + account.getBalance());
    }

    /** 🔄 Transfer Money */
    public ResponseEntity<String> transfer(Long fromAccountId, Long toAccountId, double amount) {
        Optional<Account> optionalFromAccount = accountRepository.findById(fromAccountId);
        Optional<Account> optionalToAccount = accountRepository.findById(toAccountId);

        if (!optionalFromAccount.isPresent() || !optionalToAccount.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid transfer request. One or both accounts do not exist.");
        }

        Account fromAccount = optionalFromAccount.get();
        Account toAccount = optionalToAccount.get();

        if (fromAccount.getBalance() < amount) {
            return ResponseEntity.badRequest().body("Insufficient funds for transfer.");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setAccount(fromAccount);
        transaction.setAmount(amount);
        transaction.setTransactionType("TRANSFER");
        transaction.setTimestamp(new Date());  // Fix timestamp

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transactionRepository.save(transaction);
        
        return ResponseEntity.ok("Transfer successful! New balance: " + fromAccount.getBalance());
    }

    
    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
