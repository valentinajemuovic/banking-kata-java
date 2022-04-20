package com.optivem.kata.banking.core.usecases.depositfunds;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class DepositFundsRequest {
    private String accountNumber;
    private int amount;
}
