package com.optivem.kata.banking.core.internal.cleanarch.domain.accounts;

import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.BaseDomainEvent;
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
