package com.optivem.kata.banking.core.usecases.openaccount;

import an.awesome.pipelinr.Command;
import com.optivem.kata.banking.core.domain.accounts.*;
import com.optivem.kata.banking.core.domain.time.DateTimeService;
import org.springframework.stereotype.Component;

@Component
public class OpenAccountUseCase implements Command.Handler<OpenAccountRequest, OpenAccountResponse> {

    private final AccountNumberGenerator accountNumberGenerator;
    private final DateTimeService dateTimeService;
    private final BankAccountRepository bankAccountRepository;

    public OpenAccountUseCase(AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, BankAccountRepository bankAccountRepository) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.dateTimeService = dateTimeService;
        this.bankAccountRepository = bankAccountRepository;
    }

    public OpenAccountResponse handle(OpenAccountRequest request) {
        var accountHolderName = getAccountHolderName(request);
        var balance = getBalance(request);

        var bankAccount = createBankAccount(accountHolderName, balance);
        bankAccountRepository.add(bankAccount);

        return getResponse(bankAccount);
    }

    private AccountHolderName getAccountHolderName(OpenAccountRequest request) {
        return AccountHolderName.of(request.getFirstName(), request.getLastName());
    }

    private Balance getBalance(OpenAccountRequest request) {
        return Balance.of(request.getBalance());
    }

    private BankAccount createBankAccount(AccountHolderName accountHolderName, Balance balance) {
        var accountNumber = accountNumberGenerator.next();
        var dateTime = dateTimeService.getCurrent();
        var openingDate = dateTime.toLocalDate();
        return new BankAccount(accountNumber, accountHolderName, openingDate, balance);
    }

    private OpenAccountResponse getResponse(BankAccount bankAccount) {
        var accountNumber = bankAccount.getAccountNumber().toString();

        return OpenAccountResponse.builder()
                .accountNumber(accountNumber)
                .build();
    }
}
