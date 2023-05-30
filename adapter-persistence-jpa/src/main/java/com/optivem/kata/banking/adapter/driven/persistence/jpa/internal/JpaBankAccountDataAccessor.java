package com.optivem.kata.banking.adapter.driven.persistence.jpa.internal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBankAccountDataAccessor extends CrudRepository<BankAccountRecord, Long> {
    Optional<BankAccountRecord> findByAccountNumber(String accountNumber);
}
