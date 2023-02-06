package com.optivem.kata.banking.core.internal.crud.usecases;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.ports.driven.*;
import com.optivem.kata.banking.core.ports.driven.events.AccountOpenedDto;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationException;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;

import static com.optivem.kata.banking.core.internal.crud.common.Guard.guard;

public class OpenAccountUseCase implements Command.Handler<OpenAccountRequest, OpenAccountResponse> {
    private NationalIdentityGateway nationalIdentityGateway;
    private CustomerGateway customerGateway;
    private BankAccountStorage bankAccountStorage;
    private AccountIdGenerator accountIdGenerator;
    private AccountNumberGenerator accountNumberGenerator;
    private DateTimeService dateTimeService;

    private EventBus eventBus;

    public OpenAccountUseCase(NationalIdentityGateway nationalIdentityGateway, CustomerGateway customerGateway, BankAccountStorage bankAccountStorage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, EventBus eventBus) {
        this.nationalIdentityGateway = nationalIdentityGateway;
        this.customerGateway = customerGateway;
        this.bankAccountStorage = bankAccountStorage;
        this.accountIdGenerator = accountIdGenerator;
        this.accountNumberGenerator = accountNumberGenerator;
        this.dateTimeService = dateTimeService;
        this.eventBus = eventBus;
    }

    @Override
    public OpenAccountResponse handle(OpenAccountRequest request) {
        var nationalIdentityNumber = guard(request.getNationalIdentityNumber()).againstNullOrWhitespace(ValidationMessages.NATIONAL_IDENTITY_NUMBER_EMPTY);
        var firstName = guard(request.getFirstName()).againstNullOrWhitespace(ValidationMessages.FIRST_NAME_EMPTY);
        var lastName = guard(request.getLastName()).againstNullOrWhitespace(ValidationMessages.LAST_NAME_EMPTY);
        var balance = guard(request.getBalance()).againstNegative(ValidationMessages.BALANCE_NEGATIVE);

        var nationalIdentityNumberExists = nationalIdentityGateway.exists(nationalIdentityNumber);

        if(!nationalIdentityNumberExists) {
            throw new ValidationException(ValidationMessages.NATIONAL_IDENTITY_NUMBER_NONEXISTENT);
        }

        var isBlacklisted = customerGateway.isBlacklisted(nationalIdentityNumber);

        if(isBlacklisted) {
            throw new ValidationException(ValidationMessages.NATIONAL_IDENTITY_NUMBER_BLACKLISTED);
        }

        var accountId = accountIdGenerator.next();
        var accountNumber = accountNumberGenerator.next();
        var timestamp = dateTimeService.now();
        var openingDate = timestamp.toLocalDate();

        var account = BankAccountDto.builder()
                .accountId(accountId)
                .accountNumber(accountNumber)
                .nationalIdentityNumber(nationalIdentityNumber)
                .firstName(firstName)
                .lastName(lastName)
                .openingDate(openingDate)
                .balance(balance)
                .build();

        bankAccountStorage.add(account);

        var eventDto = new AccountOpenedDto(timestamp, accountId, firstName, lastName, balance);

        eventBus.publish(eventDto);

        return OpenAccountResponse.builder()
                .accountNumber(accountNumber)
                .build();
    }
}
