# Banking Kata - Java

[![CI](https://github.com/valentinacupac/banking-kata-java/actions/workflows/ci.yaml/badge.svg)](https://github.com/valentinacupac/banking-kata-java/actions/workflows/ci.yaml)

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

## Instructions

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