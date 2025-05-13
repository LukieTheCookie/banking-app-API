package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.entity.Transaction;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto){
        return new Transaction(
                transactionDto.getId(),
                transactionDto.getAmount(),
                transactionDto.getTimestamp(),
                transactionDto.getType(),
                transactionDto.getAccount()
        );
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTimestamp(),
                transaction.getType(),
                transaction.getAccount()
        );
    }
}
