package com.example.bankingapp.dto;

import com.example.bankingapp.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password; //Todo: Replace with OAuth ID
    private List<Account> accounts;
}
