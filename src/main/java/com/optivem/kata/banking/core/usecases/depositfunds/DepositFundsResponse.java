package com.optivem.kata.banking.core.usecases.depositfunds;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class DepositFundsResponse {
    private int balance;
}
