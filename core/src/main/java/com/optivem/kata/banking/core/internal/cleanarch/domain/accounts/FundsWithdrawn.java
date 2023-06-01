package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.BaseDomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FundsWithdrawn extends BaseDomainEvent {
    private AccountNumber accountNumber;
    private TransactionAmount amount;

    public FundsWithdrawn(LocalDateTime timestamp, AccountNumber accountNumber, TransactionAmount amount) {
        super(timestamp);
        this.accountNumber = accountNumber;
        this.amount = amount;
    }
}