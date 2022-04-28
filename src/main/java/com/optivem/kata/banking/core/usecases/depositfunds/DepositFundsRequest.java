package com.optivem.kata.banking.core.usecases.depositfunds;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class DepositFundsRequest {
    private String accountNumber;
    private int amount;
}
