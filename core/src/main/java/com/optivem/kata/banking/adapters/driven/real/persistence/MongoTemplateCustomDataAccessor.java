package com.optivem.kata.banking.adapters.driven.real.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoTemplateCustomDataAccessor {

    @Autowired
    private MongoTemplate mongoTemplate;

}
