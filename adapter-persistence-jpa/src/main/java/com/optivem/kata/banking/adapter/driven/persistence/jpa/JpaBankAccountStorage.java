package com.optivem.kata.banking.adapter.driven.persistence.jpa;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.adapter.driven.persistence.jpa.internal.BankAccountRecord;
import com.optivem.kata.banking.adapter.driven.persistence.jpa.internal.JpaBankAccountDataAccessor;
import com.optivem.kata.banking.adapter.driven.persistence.jpa.mapper.BankAccountJpaMapper;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Primary
@Component
@Profile(ProfileNames.ADAPTER_PERSISTENCE_JPA)
public class JpaBankAccountStorage implements BankAccountStorage {

    private final JpaBankAccountDataAccessor dataAccessor;
    private final BankAccountJpaMapper bankAccountJpaMapper;

    public JpaBankAccountStorage(JpaBankAccountDataAccessor dataAccessor, BankAccountJpaMapper bankAccountJpaMapper) {
        this.dataAccessor = dataAccessor;
        this.bankAccountJpaMapper = bankAccountJpaMapper;
    }

    @Override
    public Optional<BankAccountDto> find(String accountNumber) {
        var bankAccountRecord = dataAccessor.findByAccountNumber(accountNumber);

        if(bankAccountRecord.isEmpty()) {
            return Optional.empty();
        }

        var bankAccount = toDto(bankAccountRecord.get());
        return Optional.of(bankAccount);
    }

    @Override
    public void add(BankAccountDto bankAccount) {
        var bankAccountRecord = create(bankAccount);
        dataAccessor.save(bankAccountRecord);
    }

    @Override
    public void update(BankAccountDto bankAccountDto) {
        var bankAccountModelOptional = findByAccountNumber(bankAccountDto.getAccountNumber());
        bankAccountModelOptional.ifPresent(updateTheModelWithInformationFrom(bankAccountDto));
    }

    private BankAccountRecord create(BankAccountDto bankAccount) {
        var bankAccountRecord = new BankAccountRecord();
        bankAccountRecord.setId(bankAccount.getAccountId());
        bankAccountRecord.setAccountNumber(bankAccount.getAccountNumber());
        bankAccountRecord.setNationalIdentityNumber(bankAccount.getNationalIdentityNumber());
        bankAccountRecord.setFirstName(bankAccount.getFirstName());
        bankAccountRecord.setLastName(bankAccount.getLastName());
        bankAccountRecord.setOpeningDate(bankAccount.getOpeningDate());
        bankAccountRecord.setBalance(bankAccount.getBalance());
        return bankAccountRecord;
    }

    private BankAccountDto toDto(BankAccountRecord bankAccountRecord) {
        var dto = new BankAccountDto();
        dto.setAccountId(bankAccountRecord.getId());
        dto.setAccountNumber(bankAccountRecord.getAccountNumber());
        dto.setNationalIdentityNumber(bankAccountRecord.getNationalIdentityNumber());
        dto.setFirstName(bankAccountRecord.getFirstName());
        dto.setLastName(bankAccountRecord.getLastName());
        dto.setOpeningDate(bankAccountRecord.getOpeningDate());
        dto.setBalance(bankAccountRecord.getBalance());
        return dto;
    }

    private Optional<BankAccountRecord> findByAccountNumber(String accountNumber) {
        return dataAccessor.findByAccountNumber(accountNumber);
    }

    private Consumer<BankAccountRecord> updateTheModelWithInformationFrom(BankAccountDto bankAccountDto) {
        return bankAccountRecord -> {
            var updatedBankAccountModel = bankAccountJpaMapper.toRecord(bankAccountDto, bankAccountRecord);
            dataAccessor.save(updatedBankAccountModel);
        };
    }
}
