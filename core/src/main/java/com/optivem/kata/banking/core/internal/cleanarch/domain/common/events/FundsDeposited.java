package com.optivem.kata.banking.core.internal.cleanarch.domain.common.events;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountId;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.Money;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.TransactionAmount;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.common.BaseDomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FundsDeposited extends BaseDomainEvent {
    private AccountNumber accountNumber;
    private TransactionAmount amount;

    public FundsDeposited(LocalDateTime timestamp, AccountNumber accountNumber, TransactionAmount amount) {
        super(timestamp);
        this.accountNumber = accountNumber;
        this.amount = amount;
    }
}
