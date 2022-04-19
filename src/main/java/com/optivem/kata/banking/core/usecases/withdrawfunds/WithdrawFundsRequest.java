package com.optivem.kata.banking.core.usecases.withdrawfunds;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class WithdrawFundsRequest {
    private String accountNumber;
    private int amount;
}
