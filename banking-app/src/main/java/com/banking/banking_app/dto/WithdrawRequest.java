package com.banking.banking_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawRequest {
    private Long accountId;
    private double amount;
}
