#!/bin/bash

export POSTGRES_USER=postgres
export POSTGRES_PASSWORD=admin
export POSTGRES_DB=banking_kata
export POSTGRES_URL=jdbc:postgresql://localhost:5432/$POSTGRES_DB;POSTGRES_USER=$POSTGRES_USER;POSTGRES_PASSWORD=$POSTGRES_PASSWORD
export KEYCLOAK_REALM_URL=http://localhost:10000/auth/realms/banking-kata
export KEYCLOAK_TEST_CLIENT_ID=test-client
export KEYCLOAK_TEST_CLIENT_SECRET=XXXX