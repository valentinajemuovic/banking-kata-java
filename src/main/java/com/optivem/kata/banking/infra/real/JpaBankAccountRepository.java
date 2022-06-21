package com.optivem.kata.banking.infra.real;

import com.optivem.kata.banking.core.domain.accounts.*;
import com.optivem.kata.banking.infra.real.persistence.BankAccountRecord;
import com.optivem.kata.banking.infra.real.persistence.JpaBankAccountDataAccessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaBankAccountRepository implements BankAccountRepository {

    private JpaBankAccountDataAccessor dataAccessor;

    public JpaBankAccountRepository(JpaBankAccountDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }
    
    @Override
    public Optional<BankAccount> find(AccountNumber accountNumber) {
        var accountNumberValue = accountNumber.toString();
        var record = dataAccessor.findByAccountNumber(accountNumberValue);

        if(record.isEmpty()) {
            return Optional.empty();
        }

        var bankAccount = get(record.get());
        return Optional.of(bankAccount);
    }

    @Override
    public void add(BankAccount bankAccount) {
        var record = create(bankAccount);
        dataAccessor.save(record);
    }

    @Override
    public void update(BankAccount bankAccount) {

    }

    private BankAccountRecord create(BankAccount bankAccount) {
        var accountId = bankAccount.getAccountId().toLong();
        var accountNumber = bankAccount.getAccountNumber().toString();
        var firstName = bankAccount.getAccountHolderName().firstName().toString();
        var lastName = bankAccount.getAccountHolderName().lastName().toString();
        var openingDate = bankAccount.getOpeningDate();
        var balance = bankAccount.getBalance().toInt();

        var record = new BankAccountRecord();
        record.setId(accountId);
        record.setAccountNumber(accountNumber);
        record.setFirstName(firstName);
        record.setLastName(lastName);
        record.setOpeningDate(openingDate);
        record.setBalance(balance);

        return record;
    }

    private BankAccount get(BankAccountRecord record) {
        var accountId = AccountId.of(record.getId());
        var accountNumber = AccountNumber.of(record.getAccountNumber());
        var accountHolderName = AccountHolderName.of(record.getFirstName(), record.getLastName());
        var openingDate = record.getOpeningDate();
        var balance = Balance.of(record.getBalance());

        return new BankAccount(accountId, accountNumber, accountHolderName, openingDate, balance);
    }
}
