package com.optivem.kata.banking.core.internal.crud;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountId;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.EventPublisher;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.events.UseCaseEvents.AccountOpenedUseCaseEvent;
import com.optivem.kata.banking.core.internal.cleanarch.domain.common.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.ports.driven.*;
import com.optivem.kata.banking.core.ports.driver.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.openaccount.OpenAccountResponse;

import static com.optivem.kata.banking.core.internal.crud.common.Guard.guard;

public class OpenAccountUseCase implements Command.Handler<OpenAccountRequest, OpenAccountResponse> {

    private BankAccountStorage bankAccountStorage;

    private AccountIdGenerator accountIdGenerator;
    private AccountNumberGenerator accountNumberGenerator;
    private DateTimeService dateTimeService;

    private EventPublisher eventPublisher;

    public OpenAccountUseCase(BankAccountStorage bankAccountStorage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, EventPublisher eventPublisher) {
        this.bankAccountStorage = bankAccountStorage;
        this.accountIdGenerator = accountIdGenerator;
        this.accountNumberGenerator = accountNumberGenerator;
        this.dateTimeService = dateTimeService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public OpenAccountResponse handle(OpenAccountRequest request) {
        var firstName = guard(request.getFirstName()).againstNullOrWhitespace(ValidationMessages.FIRST_NAME_EMPTY);
        var lastName = guard(request.getLastName()).againstNullOrWhitespace(ValidationMessages.LAST_NAME_EMPTY);
        var balance = guard(request.getBalance()).againstNegative(ValidationMessages.BALANCE_NEGATIVE);

        var accountId = accountIdGenerator.next();
        var accountNumber = accountNumberGenerator.next();
        var openingDate = dateTimeService.now().toLocalDate();

        var account = BankAccountDto.builder()
                .accountId(accountId)
                .accountNumber(accountNumber)
                .firstName(firstName)
                .lastName(lastName)
                .openingDate(openingDate)
                .balance(balance)
                .build();

        bankAccountStorage.add(account);

        // TODO: VC: Ports
        eventPublisher.publishEvent(AccountOpenedUseCaseEvent.generateEventOnSuccess(AccountId.of(accountId)));

        return OpenAccountResponse.builder()
                .accountNumber(accountNumber)
                .build();
    }
}
