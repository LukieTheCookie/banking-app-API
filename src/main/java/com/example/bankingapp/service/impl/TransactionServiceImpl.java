package com.example.bankingapp.service.impl;

import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.dto.TransactionType;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.mapper.TransactionMapper;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.repository.TransactionRepository;
import com.example.bankingapp.service.TransactionService;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public TransactionDto deposit(TransactionDto transactionDto) {
        Account account = accountRepository
                .findById(transactionDto.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double newBalance = account.getBalance() + transactionDto.getAmount();
        account.setBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAccount(account);
        transactionRepository.save(transaction);

        return TransactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    public TransactionDto withdraw(TransactionDto transactionDto) {
        Account account = accountRepository.findById(transactionDto.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < transactionDto.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        // Todo: Add amount validation
        double newBalance = account.getBalance() - transactionDto.getAmount();
        account.setBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setAccount(account);
        transactionRepository.save(transaction);

        return TransactionMapper.mapToTransactionDto(transaction);
    }
}
