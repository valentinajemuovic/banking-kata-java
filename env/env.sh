#!/bin/bash

export POSTGRES_USER=postgres
export POSTGRES_PASSWORD=admin
export POSTGRES_DB=banking_kata_db
export POSTGRES_URL=jdbc:postgresql://localhost:5400/$POSTGRES_DB;POSTGRES_USER=$POSTGRES_USER;POSTGRES_PASSWORD=$POSTGRES_PASSWORD

export MONGO_INITDB_ROOT_USERNAME=rootuser
export MONGO_INITDB_ROOT_PASSWORD=rootpass
export ME_CONFIG_MONGODB_ADMINUSERNAME=rootuser
export ME_CONFIG_MONGODB_ADMINPASSWORD=rootpass
export ME_CONFIG_MONGODB_SERVER=mongodb
export MONGODB_DATABASE_NAME=kata
export MONGODB_URI="mongodb://rootuser:rootpass@localhost:27017"

export KEYCLOAK_REALM_URL=http://localhost:10000/auth/realms/banking-kata
export KEYCLOAK_TEST_CLIENT_ID=test-client
export KEYCLOAK_TEST_CLIENT_SECRET=XXXX

export REDIS_HOST=localhost
export REDIS_PORT=6379
export REDIS_PASSWORD=b4EA2xC6LlKnlizPu9bwRRrQM