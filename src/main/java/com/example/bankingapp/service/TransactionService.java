package com.example.bankingapp.service;

import com.example.bankingapp.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto deposit(TransactionDto transactionDto);
    TransactionDto withdraw(TransactionDto transactionDto);
    List<TransactionDto> getTransactionByAccountId(Long accountId);
}
