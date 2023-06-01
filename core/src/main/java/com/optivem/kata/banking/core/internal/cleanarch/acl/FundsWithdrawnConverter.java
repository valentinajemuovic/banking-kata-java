package com.optivem.kata.banking.core.internal.cleanarch.acl;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.FundsWithdrawn;
import com.optivem.kata.banking.core.ports.driven.events.FundsWithdrawnDto;

public class FundsWithdrawnConverter {
    public static FundsWithdrawnDto fromEvent(FundsWithdrawn event) {
        return FundsWithdrawnDto.builder()
                .timestamp(event.getTimestamp())
                .accountNumber(event.getAccountNumber().value().toString())
                .amount(event.getAmount().value().value())
                .build();
    }
}
