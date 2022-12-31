package com.optivem.kata.banking.adapters.persistence.jpa;

import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class JpaBankAccountStorage implements BankAccountStorage {

    private JpaBankAccountDataAccessor dataAccessor;

    public JpaBankAccountStorage(JpaBankAccountDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }
    
    @Override
    public Optional<BankAccountDto> find(String accountNumber) {
        var accountNumberValue = accountNumber.toString();
        var record = dataAccessor.findByAccountNumber(accountNumberValue);

        if(record.isEmpty()) {
            return Optional.empty();
        }

        var bankAccount = get(record.get());
        return Optional.of(bankAccount);
    }

    @Override
    public void add(BankAccountDto bankAccount) {
        var record = create(bankAccount);
        dataAccessor.save(record);
    }

    @Override
    public void update(BankAccountDto bankAccount) {

    }

    private BankAccountRecord create(BankAccountDto bankAccount) {
        var record = new BankAccountRecord();
        record.setId(bankAccount.getAccountId());
        record.setAccountNumber(bankAccount.getAccountNumber());
        record.setFirstName(bankAccount.getFirstName());
        record.setLastName(bankAccount.getLastName());
        record.setOpeningDate(bankAccount.getOpeningDate());
        record.setBalance(bankAccount.getBalance());
        return record;
    }

    private BankAccountDto get(BankAccountRecord record) {
        var dto = new BankAccountDto();
        dto.setAccountId(record.getId());
        dto.setAccountNumber(record.getAccountNumber());
        dto.setFirstName(record.getFirstName());
        dto.setLastName(record.getLastName());
        dto.setOpeningDate(record.getOpeningDate());
        dto.setBalance(record.getBalance());
        return dto;
    }
}
