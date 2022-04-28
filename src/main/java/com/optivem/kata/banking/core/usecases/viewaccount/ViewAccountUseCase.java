package com.optivem.kata.banking.core.usecases.viewaccount;

import com.optivem.kata.banking.core.usecases.UseCase;

public class ViewAccountUseCase implements UseCase<ViewAccountRequest, ViewAccountResponse> {
    @Override
    public ViewAccountResponse handle(ViewAccountRequest request) {
        return new ViewAccountResponse();
    }
}
