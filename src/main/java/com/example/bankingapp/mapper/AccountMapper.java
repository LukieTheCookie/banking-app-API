package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account();

        account.setId(accountDto.getId());
        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setBalance(accountDto.getBalance());
        account.setTransactions(accountDto.getTransactions());

        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getUser() != null ? account.getUser().getId() : null,
                account.getTransactions()
        );
    }
}
