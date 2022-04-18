package com.optivem.kata.banking.core.usecases.openaccount;

import com.optivem.kata.banking.core.common.Guard;
import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;

public class OpenAccountUseCase {

    public OpenAccountResponse handle(OpenAccountRequest request) {
        Guard.AgainstNullOrWhitespace(request.getFirstName(), ValidationMessages.FIRST_NAME_EMPTY);
        Guard.AgainstNullOrWhitespace(request.getLastName(), ValidationMessages.LAST_NAME_EMPTY);

        return new OpenAccountResponse();
    }
}
