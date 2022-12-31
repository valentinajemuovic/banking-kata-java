package com.optivem.kata.banking.adapters.persistence.mongo;

import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoBankAccountStorage implements BankAccountStorage {

    private MongoTemplateCustomDataAccessor dataAccessor;

    public MongoBankAccountStorage(MongoTemplateCustomDataAccessor dataAccessor){
        this.dataAccessor=dataAccessor;
    }

    @Override
    public Optional<BankAccountDto> find(String accountNumber) {
        //To Be Implemented
        return null;
    }

    @Override
    public void add(BankAccountDto bankAccount) {
        //To Be Implemented
    }

    @Override
    public void update(BankAccountDto bankAccount) {
        //To Be Implemented
    }
}
