package com.example.bankingapp.dto;

import com.example.bankingapp.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
    private Long userId;
    private List<Transaction> transactions;
}
