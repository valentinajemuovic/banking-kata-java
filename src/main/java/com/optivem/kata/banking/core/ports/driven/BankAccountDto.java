package com.optivem.kata.banking.core.ports.driven;

import com.optivem.kata.banking.core.domain.accounts.AccountHolderName;
import com.optivem.kata.banking.core.domain.accounts.AccountId;
import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.domain.accounts.Balance;
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
    private String firstName;
    private String lastName;
    private LocalDate openingDate;
    private int balance;
}
