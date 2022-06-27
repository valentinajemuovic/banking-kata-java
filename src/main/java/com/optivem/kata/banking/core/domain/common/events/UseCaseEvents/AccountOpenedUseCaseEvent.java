package com.optivem.kata.banking.core.domain.common.events.UseCaseEvents;


import com.optivem.kata.banking.core.domain.accounts.AccountId;
import com.optivem.kata.banking.core.domain.common.events.UseCaseEvent;

public class AccountOpenedUseCaseEvent {

    public static UseCaseEvent generateEventOnSuccess(AccountId id){
        return new UseCaseEvent(id,"AccountOpened");
    }


}
