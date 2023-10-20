package com.optivem.kata.banking.core.facade.common;

import com.optivem.kata.banking.core.Facade;
import com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder;

public record TestFacade(Facade facade) {

    public String alreadyHasBankAccount(int balance) {
        var openAccountRequest = OpenAccountRequestBuilder.openAccountRequest()
                .withBalance(balance)
                .build();

        var openAccountResponse = facade.execute(openAccountRequest);
        return openAccountResponse.getAccountNumber();
    }
}
