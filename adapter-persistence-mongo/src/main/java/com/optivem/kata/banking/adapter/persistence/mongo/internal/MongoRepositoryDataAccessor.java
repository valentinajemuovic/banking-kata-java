package com.optivem.kata.banking.adapter.persistence.mongo.internal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepositoryDataAccessor extends MongoRepository<BankAccountDocument, String> {

}
