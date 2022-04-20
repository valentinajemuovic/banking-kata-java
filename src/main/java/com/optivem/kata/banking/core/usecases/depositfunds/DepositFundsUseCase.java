package com.optivem.kata.banking.core.usecases.depositfunds;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.UseCase;

public class DepositFundsUseCase implements UseCase<DepositFundsRequest, DepositFundsResponse> {
    @Override
    public DepositFundsResponse handle(DepositFundsRequest request) {
        Guard.againstNullOrWhitespace(request.getAccountNumber(), ValidationMessages.ACCOUNT_NUMBER_EMPTY);

        return new DepositFundsResponse();
    }
}
