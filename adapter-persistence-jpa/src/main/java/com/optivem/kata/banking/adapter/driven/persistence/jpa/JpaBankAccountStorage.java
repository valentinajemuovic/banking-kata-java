package com.optivem.kata.banking.adapter.driven.persistence.jpa;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.adapter.driven.persistence.jpa.internal.BankAccountRecord;
import com.optivem.kata.banking.adapter.driven.persistence.jpa.internal.JpaBankAccountDataAccessor;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile(ProfileNames.ADAPTER_PERSISTENCE_JPA)
public class JpaBankAccountStorage implements BankAccountStorage {

    private final JpaBankAccountDataAccessor dataAccessor;

    public JpaBankAccountStorage(JpaBankAccountDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }
    
    @Override
    public Optional<BankAccountDto> find(String accountNumber) {
        var record = dataAccessor.findByAccountNumber(accountNumber);

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
        record.setNationalIdentityNumber(bankAccount.getNationalIdentityNumber());
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
        dto.setNationalIdentityNumber(record.getNationalIdentityNumber());
        dto.setFirstName(record.getFirstName());
        dto.setLastName(record.getLastName());
        dto.setOpeningDate(record.getOpeningDate());
        dto.setBalance(record.getBalance());
        return dto;
    }
}
