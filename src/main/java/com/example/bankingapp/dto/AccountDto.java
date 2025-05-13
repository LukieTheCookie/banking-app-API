package com.example.bankingapp.dto;

import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
    private User user;
    private List<Transaction> transactions;
}
