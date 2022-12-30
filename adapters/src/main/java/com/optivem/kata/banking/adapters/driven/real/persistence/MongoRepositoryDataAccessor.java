package com.optivem.kata.banking.adapters.driven.real.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepositoryDataAccessor extends MongoRepository<BankAccountDocument, String> {

}
