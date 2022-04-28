package com.optivem.kata.banking.core.usecases.viewaccount;

import com.optivem.kata.banking.core.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.usecases.UseCase;

public class ViewAccountUseCase implements UseCase<ViewAccountRequest, ViewAccountResponse> {
    @Override
    public ViewAccountResponse handle(ViewAccountRequest request) {
        var accountNumber = getAccountNumber(request);
        return new ViewAccountResponse();
    }

    private AccountNumber getAccountNumber(ViewAccountRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }
}
