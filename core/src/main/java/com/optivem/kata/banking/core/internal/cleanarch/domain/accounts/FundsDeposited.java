package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.BaseDomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FundsDeposited extends BaseDomainEvent {
    private final AccountNumber accountNumber;
    private final TransactionAmount amount;

    public FundsDeposited(LocalDateTime timestamp, AccountNumber accountNumber, TransactionAmount amount) {
        super(timestamp);
        this.accountNumber = accountNumber;
        this.amount = amount;
    }
}
