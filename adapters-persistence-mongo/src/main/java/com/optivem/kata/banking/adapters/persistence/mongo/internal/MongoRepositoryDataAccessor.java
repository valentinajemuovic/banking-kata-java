package com.optivem.kata.banking.adapters.persistence.mongo.internal;

import com.optivem.kata.banking.adapters.persistence.mongo.internal.BankAccountDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepositoryDataAccessor extends MongoRepository<BankAccountDocument, String> {

}
