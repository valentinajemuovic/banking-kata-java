package com.optivem.kata.banking.core.usecases.withdrawfunds;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class WithdrawFundsResponse {
    private int balance;
}
