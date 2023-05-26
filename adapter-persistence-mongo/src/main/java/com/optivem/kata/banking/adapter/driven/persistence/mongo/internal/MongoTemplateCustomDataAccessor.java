package com.optivem.kata.banking.adapter.driven.persistence.mongo.internal;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoTemplateCustomDataAccessor {
    private final MongoClient mongoClient;
    private final MongoTemplate mongoTemplate;

    public MongoTemplateCustomDataAccessor() {
        mongoClient = MongoClients.create(System.getenv("MONGODB_URI"));
        MongoDatabase database = mongoClient.getDatabase(System.getenv("MONGODB_DATABASE_NAME"));
        mongoTemplate = new MongoTemplate(mongoClient, database.getName());
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void close() {
        mongoClient.close();
    }
}
