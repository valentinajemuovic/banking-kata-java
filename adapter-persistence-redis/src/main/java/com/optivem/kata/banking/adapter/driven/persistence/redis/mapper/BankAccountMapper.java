package com.optivem.kata.banking.adapter.driven.persistence.redis.mapper;

import com.optivem.kata.banking.adapter.driven.persistence.redis.internal.BankAccountModel;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountDto toDto(BankAccountModel model);

    BankAccountModel toModel(BankAccountDto dto);

    @Mapping(target = "accountId", source = "model.accountId")
    @Mapping(target = "accountNumber", source = "model.accountNumber")
    @Mapping(target = "nationalIdentityNumber", source = "dto.nationalIdentityNumber", defaultExpression = "java(model.getNationalIdentityNumber())")
    @Mapping(target = "firstName", source = "dto.firstName", defaultExpression = "java(model.getFirstName())")
    @Mapping(target = "lastName", source = "dto.lastName", defaultExpression = "java(model.getLastName())")
    @Mapping(target = "openingDate", source = "model.openingDate")
    @Mapping(target = "balance", source = "dto.balance", defaultExpression = "java(model.getBalance())")
    BankAccountModel toModel(BankAccountDto dto, BankAccountModel model);
}
