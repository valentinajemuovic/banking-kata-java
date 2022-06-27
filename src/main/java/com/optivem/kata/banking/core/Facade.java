package com.optivem.kata.banking.core;

import com.optivem.kata.banking.core.cleanarch.acl.BankAccountRepositoryImpl;
import com.optivem.kata.banking.core.cleanarch.domain.scoring.DefaultScoreCalculator;
import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import com.optivem.kata.banking.core.ports.driven.DateTimeService;
import com.optivem.kata.banking.core.ports.driver.depositfunds.DepositFundsRequest;
import com.optivem.kata.banking.core.cleanarch.usecases.DepositFundsUseCase;
import com.optivem.kata.banking.core.ports.driver.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.cleanarch.usecases.OpenAccountUseCase;
import com.optivem.kata.banking.core.ports.driver.viewaccount.ViewAccountRequest;
import com.optivem.kata.banking.core.ports.driver.viewaccount.ViewAccountResponse;
import com.optivem.kata.banking.core.cleanarch.usecases.ViewAccountUseCase;

public class Facade {

    private final DepositFundsUseCase depositFundsUseCase;
    private final OpenAccountUseCase openAccountUseCase;
    private final ViewAccountUseCase viewAccountUseCase;

    // TODO: VC: Perhaps server-side API facade? And server-side API facade?
    public Facade(AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, BankAccountStorage bankAccountStorage) {
        var scoreCalculator = DefaultScoreCalculator.create(dateTimeService);
        var bankAccountRepository = new BankAccountRepositoryImpl(bankAccountStorage, accountIdGenerator, accountNumberGenerator);

        this.depositFundsUseCase = new DepositFundsUseCase(bankAccountRepository);
        this.openAccountUseCase = new OpenAccountUseCase(bankAccountRepository, dateTimeService);
        this.viewAccountUseCase = new ViewAccountUseCase(bankAccountRepository, scoreCalculator);
    }

    public void execute(DepositFundsRequest request) {
        depositFundsUseCase.handle(request);
    }

    public OpenAccountResponse execute(OpenAccountRequest request) {
        return openAccountUseCase.handle(request);
    }

    public ViewAccountResponse execute(ViewAccountRequest request) {
        return viewAccountUseCase.handle(request);
    }
}
