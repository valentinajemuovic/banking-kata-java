package com.optivem.kata.banking.core.internal.cleanarch.domain.common.events;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountHolderName;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountId;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.Balance;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.common.BaseDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AccountOpened extends BaseDomainEvent {
    private AccountId accountId;
    private AccountHolderName accountHolderName;
    private Balance balance;

    public AccountOpened(LocalDateTime timestamp, AccountId accountId, AccountHolderName accountHolderName, Balance balance) {
        super(timestamp);
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
}
