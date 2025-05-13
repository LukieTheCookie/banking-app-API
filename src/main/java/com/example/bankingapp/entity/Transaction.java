package com.example.bankingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    private double amount;
    private LocalDateTime timestamp;
    private String type; //Todo: create enum with types (Deposit, Withdraw, etc)

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
