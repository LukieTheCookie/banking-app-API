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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
                .findById(transactionDto.getAccountId())
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
    @Transactional
    public TransactionDto withdraw(TransactionDto transactionDto) {
        Account account = accountRepository.findById(transactionDto.getAccountId())
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

    @Override
    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        List<Transaction> transactions = transactionRepository.findByAccount(account);

        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }
}
