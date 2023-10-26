package com.optivem.kata.banking.adapter.driven.persistence.mongo.internal;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoTemplateCustomDataAccessor {
    private final MongoClient mongoClient;
    @Getter
    private final MongoTemplate mongoTemplate;

    public MongoTemplateCustomDataAccessor() {
        mongoClient = MongoClients.create(System.getenv("MONGODB_URI"));
        MongoDatabase database = mongoClient.getDatabase(System.getenv("MONGODB_DATABASE_NAME"));
        mongoTemplate = new MongoTemplate(mongoClient, database.getName());
    }

    public void close() {
        mongoClient.close();
    }
}
