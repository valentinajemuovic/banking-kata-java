package com.optivem.kata.banking.core;

import com.optivem.kata.banking.core.domain.scoring.DefaultScoreCalculator;
import com.optivem.kata.banking.core.gateways.AccountIdGenerator;
import com.optivem.kata.banking.core.gateways.AccountNumberGenerator;
import com.optivem.kata.banking.core.gateways.BankAccountRepository;
import com.optivem.kata.banking.core.domain.accounts.scoring.*;
import com.optivem.kata.banking.core.gateways.DateTimeService;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsRequest;
import com.optivem.kata.banking.core.usecases.depositfunds.DepositFundsUseCase;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountUseCase;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountRequest;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountResponse;
import com.optivem.kata.banking.core.usecases.viewaccount.ViewAccountUseCase;

public class Facade {

    private final DepositFundsUseCase depositFundsUseCase;
    private final OpenAccountUseCase openAccountUseCase;
    private final ViewAccountUseCase viewAccountUseCase;

    // TODO: VC: Perhaps server-side API facade? And server-side API facade?
    public Facade(AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator, DateTimeService dateTimeService, BankAccountRepository bankAccountRepository) {
        var scoreCalculator = DefaultScoreCalculator.create(dateTimeService);

        this.depositFundsUseCase = new DepositFundsUseCase(bankAccountRepository);
        this.openAccountUseCase = new OpenAccountUseCase(accountIdGenerator, accountNumberGenerator, dateTimeService, bankAccountRepository);
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
