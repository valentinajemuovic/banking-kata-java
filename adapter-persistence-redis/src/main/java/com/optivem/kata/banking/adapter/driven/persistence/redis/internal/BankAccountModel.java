package com.optivem.kata.banking.adapter.driven.persistence.redis.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@RedisHash("bank_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountModel {

    @Id
    private String accountNumber;
    private long accountId;
    private String nationalIdentityNumber;
    private String firstName;
    private String lastName;
    private LocalDate openingDate;
    private int balance;
}
