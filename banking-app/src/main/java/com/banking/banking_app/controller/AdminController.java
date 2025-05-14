package com.banking.banking_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_app.model.Account;
import com.banking.banking_app.model.Transaction;
import com.banking.banking_app.model.User;
import com.banking.banking_app.service.AccountService;
import com.banking.banking_app.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/create-account")
    public ResponseEntity<String> createAccount(@RequestBody AccountRequest request) {
    	ResponseEntity.ok("Hello");
        accountService.createAccount(request.getUserId(), request.getAccountType(), request.getInitialBalance());
        return ResponseEntity.ok("Account created successfully");
    }
 

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(adminService.getAllAccounts());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(adminService.getAllTransactions());
    }
    
    /** Delete a User by ID */
    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
    public static class AccountRequest {
        private Long userId;
        private String accountType;
        private double initialBalance;

        // Getters and Setters
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getAccountType() { return accountType; }
        public void setAccountType(String accountType) { this.accountType = accountType; }
        public double getInitialBalance() { return initialBalance; }
        public void setInitialBalance(double initialBalance) { this.initialBalance = initialBalance; }
    }
}
