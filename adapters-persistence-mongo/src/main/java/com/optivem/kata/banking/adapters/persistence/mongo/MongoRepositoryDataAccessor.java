package com.optivem.kata.banking.adapters.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepositoryDataAccessor extends MongoRepository<BankAccountDocument, String> {

}
