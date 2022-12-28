# Banking Kata - Java

[![CI](https://github.com/valentinacupac/banking-kata-java/actions/workflows/ci.yaml/badge.svg)](https://github.com/valentinacupac/banking-kata-java/actions/workflows/ci.yaml)

## Purpose

This demo was created for the purposes of meetup series on TDD & Clean Architecture. See the [YouTube Meetups](https://journal.optivem.com/p/foundations-of-tdd-and-clean-architecture). Please note that this project is purely for demo purposes only.

## Overview

This project illustrates TDD & Clean Architecture implementation in Java, showing the Use Case Driven Development
Approach.

We implement a Banking system with the following use cases:

- Open account
- Withdraw funds
- Deposit funds
- View account

## Prerequisites

- OpenJDK 17
- Docker

Note: If you also want to run without Docker, you can install:
- [PostgresSQL 14.4](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
- [pgAdmin 4](https://www.pgadmin.org/download/)
- [Keycloak](https://www.keycloak.org/docs/11.0/getting_started/)

## Environment variables

To be able to run the tests (since some of the tests are dependent on the database - the integration tests), we then need to set the environment variables.

Note: Soon we plan to also separate the tests so that they can be run separately.

In IntelliJ, for the `Tests in 'banking-kata.test'` configuration, you can copy this into the `Environment variables`

```
POSTGRES_URL=jdbc:postgresql://localhost:5432/banking_kata;POSTGRES_USER=postgres;POSTGRES_PASSWORD=admin;
KEYCLOAK_REALM_URL=http://localhost:10000/auth/realms/banking-kata;KEYCLOAK_TEST_CLIENT_ID=test-client;
KEYCLOAK_TEST_CLIENT_SECRET=XXXX
```

You need to have created the database, in the example I had created a database called `banking_kata`. 

Keycloak settings:
- You need to have created a keycloak realm with client_credentials flow enabled.
- In the example I had created a realm called `banking-kata` with a client_id `test-client`.

Please update the environment variable values based on your local settings.

## Running build

Environment Variables

```
$env:POSTGRES_URL='jdbc:postgresql://localhost:5432/banking_kata'
$env:POSTGRES_USER='postgres'
$env:POSTGRES_PASSWORD='admin'
$env:KEYCLOAK_REALM_URL='http://localhost:10000/auth/realms/banking-kata'
$env:KEYCLOAK_TEST_CLIENT_ID='test-client'
$env:KEYCLOAK_TEST_CLIENT_SECRET='XXXX'
```

Running build with automated tests:

```
./gradlew build
```

Running JaCoCo code coverage:

```
./gradlew jacocoTestReport
```

Running PIT mutation testing:

```
./gradlew pitest
```

## Reports

See the `build\reports` directory for the generated reports for test results, code coverage and mutation testing.

Reports:

- build\reports\tests
- build\reports\jacoco
- build\reports\pitest

## Contributing

If you'd like to contribute, see instructions here https://github.com/valentinacupac/banking-kata-java/blob/main/CONTRIBUTING.md
