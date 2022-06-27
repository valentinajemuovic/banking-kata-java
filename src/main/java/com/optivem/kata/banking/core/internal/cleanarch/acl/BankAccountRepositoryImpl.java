package com.optivem.kata.banking.core.internal.cleanarch.acl;

import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountId;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.AccountNumber;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccount;
import com.optivem.kata.banking.core.internal.cleanarch.domain.accounts.BankAccountRepository;
import com.optivem.kata.banking.core.ports.driven.AccountIdGenerator;
import com.optivem.kata.banking.core.ports.driven.AccountNumberGenerator;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankAccountRepositoryImpl implements BankAccountRepository {
    private final BankAccountStorage bankAccountStorage;
    private final AccountIdGenerator accountIdGenerator;
    private final AccountNumberGenerator accountNumberGenerator;

    public BankAccountRepositoryImpl(BankAccountStorage bankAccountStorage, AccountIdGenerator accountIdGenerator, AccountNumberGenerator accountNumberGenerator) {
        this.bankAccountStorage = bankAccountStorage;
        this.accountIdGenerator = accountIdGenerator;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    @Override
    public Optional<BankAccount> find(AccountNumber accountNumber) {
        var dto = bankAccountStorage.find(accountNumber.toString());

        if(dto.isEmpty()) {
            return Optional.empty();
        }

        var entity = BankAccountConverter.toEntity(dto.get());
        return Optional.of(entity);
    }

    @Override
    public void add(BankAccount bankAccount) {
        var dto = BankAccountConverter.toDto(bankAccount);
        bankAccountStorage.add(dto);
    }

    @Override
    public void update(BankAccount bankAccount) {
        var dto = BankAccountConverter.toDto(bankAccount);
        bankAccountStorage.update(dto);
    }

    @Override
    public AccountId nextAccountId() {
        var accountId = accountIdGenerator.next();
        return AccountId.of(accountId);
    }

    @Override
    public AccountNumber nextAccountNumber() {
        var accountNumber = accountNumberGenerator.next();
        return AccountNumber.of(accountNumber);
    }
}
