package com.example.bankingapp.service;

import com.example.bankingapp.dto.TransactionDto;

public interface TransactionService {
    TransactionDto deposit(TransactionDto transactionDto);
    TransactionDto withdraw(TransactionDto transactionDto);
}
