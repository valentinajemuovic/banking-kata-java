package com.optivem.kata.banking.adapter.driven.persistence.redis.internal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedisBankAccountDataAccessor extends CrudRepository<BankAccountModel, Long> {

    Optional<BankAccountModel> findByAccountNumber(String accountNumber);
}