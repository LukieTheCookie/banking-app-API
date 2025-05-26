package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account();

        account.setId(accountDto.getId());
        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setBalance(accountDto.getBalance());
//        account.setTransactions(accountDto.getTransactions());

        if (accountDto.getTransactions() != null) {
            List<Transaction> transactions = accountDto.getTransactions().stream()
                    .map(dto -> TransactionMapper.mapToTransaction(dto, account))
                    .collect(Collectors.toList());
            account.setTransactions(transactions);
        }

        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        List<TransactionDto> transactionDtos = account.getTransactions() != null
                ? account.getTransactions()
                        .stream()
                        .map(TransactionMapper::mapToTransactionDto)
                        .toList()
                : null;

        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getUser() != null ? account.getUser().getId() : null,
                transactionDtos
        );
    }
}
