package com.optivem.kata.banking.core.common.factories;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.ports.driven.*;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;

public interface UseCaseFactory {

    Command.Handler<OpenAccountRequest, OpenAccountResponse> createOpenAccountHandler(BankAccountStorage bankAccountStorage,
         AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator,  DateTimeService dateTimeService, EventBus eventBus);
}
