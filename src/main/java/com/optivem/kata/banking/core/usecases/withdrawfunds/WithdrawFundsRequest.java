package com.optivem.kata.banking.core.usecases.withdrawfunds;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class WithdrawFundsRequest {
    private String accountNumber;
    private int amount;
}
