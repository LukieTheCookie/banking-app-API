package com.example.bankingapp.dto;

import com.example.bankingapp.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private double amount;
    private LocalDateTime timestamp;
    private TransactionType type; //Todo: create enum with types (Deposit, Withdraw, etc)
    private Long accountId;
}
