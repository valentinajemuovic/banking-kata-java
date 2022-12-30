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

We also have authentication with Keycloak - a realm with client_id and with client_credentials flow enabled.

## Prerequisites

- OpenJDK 17
- Docker

## Basic Setup

### Build & Unit Tests

```
./gradlew build
```

_Alternatively, if you prefer to use IntelliJ UI, you can run `Tests in banking-kata.test`._

### Environment Variables

_Environment variables are located inside the `env` folder. You can optionally choose to edit them._

Apply the environment variables (for Windows only):

```shell
. .\env\env.ps1
```

Apply the environment variables (for Linux/Mac only):

```shell
source ./env/env.sh
```

_Alternatively, if you prefer to use IntelliJ UI, you can copy-paste the text from the file `env/env.intellij.ui` into the `Environment variables` section in `Tests in 'banking-kata.integrationTest'` configuration._

### Docker

For Mac only, you need to build a custom Keycloak image to enable Keycloak to work on Mac M1. 
This is due to a reported Mac-specific issue https://github.com/docker/for-mac/issues/5310.
For any other OS, please skip this step, because this issue is Mac-specific:

```shell
./src/main/resources/keycloak/build-keycloak-image-m1.zsh 16.0.0
```

Run Docker with the environment file:

```shell
docker-compose --env-file=env/.env.local up -d
```

_Alternatively, run Docker without the environment file:_

```shell
docker-compose up -d
```

### Integration Tests

Based on having successfully set Environment Variables & Docker (see above), you can run the Integration Tests:

```shell
./gradlew integrationTest
```

_Alternatively, if you prefer to use IntelliJ UI, you can run `Tests in 'banking-kata.integrationTest'` (assuming you've configured the environment variables also via UI, as per steps above)._

### Code Coverage

Run JaCoCo code coverage:

```
./gradlew jacocoTestReport
```

### Mutation Testing

Run PIT mutation testing:

```
./gradlew pitest
```

## Reports

See the `build\reports` directory for the generated reports for test results, code coverage and mutation testing.

Reports:

- build\reports\tests
- build\reports\jacoco
- build\reports\pitest

## Issues

If you experience Integration Tests failing, please see the following known issue https://github.com/valentinacupac/banking-kata-java/issues/64.

If you experience any other issues, please create a ticket https://github.com/valentinacupac/banking-kata-java/issues/new

## Contributing

If you'd like to contribute, see instructions here https://github.com/valentinacupac/banking-kata-java/blob/main/CONTRIBUTING.md

The following are our existing contributors:
- [Valentina Cupać](https://www.linkedin.com/in/valentinacupac/) ([valentinacupac](https://github.com/valentinacupac))
- [Amin Talukder](https://www.linkedin.com/in/amin-talukder/) ([eamtalu](https://github.com/eamtalu))
- [Adrián Lizaga](https://www.linkedin.com/in/adrian-lizaga/) ([adrianliz](https://github.com/adrianliz))
- [Franco Lombardo](https://www.linkedin.com/in/francolombardo/) ([f-lombardo](https://github.com/f-lombardo))
- [Donald Siziba](https://www.linkedin.com/in/donald-siziba-35603322/) ([donaldsiziba](https://github.com/donaldsiziba))
