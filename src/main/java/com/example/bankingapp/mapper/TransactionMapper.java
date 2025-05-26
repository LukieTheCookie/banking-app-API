package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Transaction;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto, Account account){
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTimestamp(transactionDto.getTimestamp());
        transaction.setType(transactionDto.getType());
        transaction.setAccount(account);
        return transaction;
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTimestamp(),
                transaction.getType(),
                transaction.getAccount() != null ? transaction.getAccount().getId() : null
        );
    }
}
