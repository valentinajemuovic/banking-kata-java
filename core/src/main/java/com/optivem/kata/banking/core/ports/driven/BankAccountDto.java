package com.optivem.kata.banking.core.ports.driven;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BankAccountDto {
    private long accountId;
    private String accountNumber;
    private String nationalIdentityNumber;
    private String firstName;
    private String lastName;
    private LocalDate openingDate;
    private int balance;
}
