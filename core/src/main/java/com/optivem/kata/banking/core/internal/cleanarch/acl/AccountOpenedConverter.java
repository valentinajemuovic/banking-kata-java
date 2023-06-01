package com.optivem.kata.banking.core.internal.cleanarch.acl;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountOpened;
import com.optivem.kata.banking.core.ports.driven.events.AccountOpenedDto;

public class AccountOpenedConverter {
    public static AccountOpenedDto fromEvent(AccountOpened event) {
        return AccountOpenedDto.builder()
                .timestamp(event.getTimestamp())
                .accountId(event.getAccountId().toLong())
                .firstName(event.getAccountHolderName().firstName().toString())
                .lastName(event.getAccountHolderName().lastName().toString())
                .balance(event.getBalance().toInt())
                .build();
    }
}
