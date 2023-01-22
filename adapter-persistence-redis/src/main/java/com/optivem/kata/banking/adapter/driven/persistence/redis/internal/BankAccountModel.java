package com.optivem.kata.banking.adapter.driven.persistence.redis.internal;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDate;

@RedisHash("bank_account")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountModel {

    @Id
    private Long accountId;
    @Indexed
    private String accountNumber;
    private String nationalIdentityNumber;
    private String firstName;
    private String lastName;
    private LocalDate openingDate;
    private int balance;
}
