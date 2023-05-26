package com.optivem.kata.banking.adapter.driven.persistence.mongo;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.adapter.driven.persistence.mongo.internal.MongoTemplateCustomDataAccessor;
import com.optivem.kata.banking.core.ports.driven.BankAccountDto;
import com.optivem.kata.banking.core.ports.driven.BankAccountStorage;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@Profile(ProfileNames.ADAPTER_PERSISTENCE_MONGO)
public class MongoBankAccountStorage implements BankAccountStorage {
    public final String collectionName = "bank_account";

    private final MongoTemplateCustomDataAccessor dataAccessor;

    public MongoBankAccountStorage(MongoTemplateCustomDataAccessor dataAccessor){
        this.dataAccessor = dataAccessor;
    }

    @Override
    public Optional<BankAccountDto> find(String accountNumber) {
        MongoTemplate mongoTemplate = this.dataAccessor.getMongoTemplate();
        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(accountNumber));
        return Optional.ofNullable(mongoTemplate.findOne(query, BankAccountDto.class, collectionName));
    }

    @Override
    public void add(BankAccountDto bankAccount) {
        MongoTemplate mongoTemplate = this.dataAccessor.getMongoTemplate();
        mongoTemplate.save(bankAccount, this.collectionName);
    }

    @Override
    public void update(BankAccountDto bankAccount) {
        MongoTemplate mongoTemplate = this.dataAccessor.getMongoTemplate();
        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(bankAccount.getAccountNumber()));
        Update update = new Update();
        update.set("firstName", bankAccount.getFirstName());
        update.set("lastName", bankAccount.getLastName());
        update.set("balance", bankAccount.getBalance());
        mongoTemplate.updateFirst(query, update, this.collectionName);
    }
}
