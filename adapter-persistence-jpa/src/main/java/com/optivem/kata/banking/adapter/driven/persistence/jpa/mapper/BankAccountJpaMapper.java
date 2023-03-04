package com.optivem.kata.banking.adapter.driven.persistence.jpa.mapper;

import com.optivem.kata.banking.adapter.driven.persistence.jpa.internal.BankAccountRecord;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankAccountJpaMapper {

    @Mapping(target = "id", source = "bankAccountRecord.id")
    @Mapping(target = "accountNumber", source = "bankAccountRecord.accountNumber")
    @Mapping(target = "nationalIdentityNumber", source = "dto.nationalIdentityNumber", defaultExpression = "java(bankAccountRecord.getNationalIdentityNumber())")
    @Mapping(target = "firstName", source = "dto.firstName", defaultExpression = "java(bankAccountRecord.getFirstName())")
    @Mapping(target = "lastName", source = "dto.lastName", defaultExpression = "java(bankAccountRecord.getLastName())")
    @Mapping(target = "openingDate", source = "bankAccountRecord.openingDate")
    @Mapping(target = "balance", source = "dto.balance", defaultExpression = "java(bankAccountRecord.getBalance())")
    BankAccountRecord toRecord(BankAccountDto dto, BankAccountRecord bankAccountRecord);
}
