core
├── build.gradle
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── optivem
│   │               └── kata
│   │                   └── banking
│   │                       └── core
│   │                           ├── Facade.java
│   │                           ├── internal
│   │                           │   ├── cleanarch
│   │                           │   │   ├── acl
│   │                           │   │   │   ├── AccountOpenedConverter.java
│   │                           │   │   │   ├── BankAccountConverter.java
│   │                           │   │   │   ├── BankAccountRepositoryImpl.java
│   │                           │   │   │   ├── EventPublisherImpl.java
│   │                           │   │   │   ├── FundsDepositedConverter.java
│   │                           │   │   │   └── FundsWithdrawnConverter.java
│   │                           │   │   ├── domain
│   │                           │   │   │   ├── accounts
│   │                           │   │   │   │   ├── AccountHolderName.java
│   │                           │   │   │   │   ├── AccountId.java
│   │                           │   │   │   │   ├── AccountNumber.java
│   │                           │   │   │   │   ├── AccountOpened.java
│   │                           │   │   │   │   ├── Balance.java
│   │                           │   │   │   │   ├── BankAccount.java
│   │                           │   │   │   │   ├── BankAccountBuilder.java
│   │                           │   │   │   │   ├── BankAccountRepository.java
│   │                           │   │   │   │   ├── FundsDeposited.java
│   │                           │   │   │   │   ├── FundsWithdrawn.java
│   │                           │   │   │   │   ├── Money.java
│   │                           │   │   │   │   └── TransactionAmount.java
│   │                           │   │   │   ├── common
│   │                           │   │   │   │   ├── events
│   │                           │   │   │   │   │   ├── BaseDomainEvent.java
│   │                           │   │   │   │   │   ├── DomainEvent.java
│   │                           │   │   │   │   │   └── EventPublisher.java
│   │                           │   │   │   │   ├── exceptions
│   │                           │   │   │   │   │   ├── RepositoryException.java
│   │                           │   │   │   │   │   └── RepositoryMessages.java
│   │                           │   │   │   │   ├── generation
│   │                           │   │   │   │   │   └── Generator.java
│   │                           │   │   │   │   ├── guards
│   │                           │   │   │   │   │   ├── BaseGuard.java
│   │                           │   │   │   │   │   ├── LongGuard.java
│   │                           │   │   │   │   │   ├── MoneyGuard.java
│   │                           │   │   │   │   │   └── TextGuard.java
│   │                           │   │   │   │   └── scoring
│   │                           │   │   │   │       ├── BalanceFactorCalculator.java
│   │                           │   │   │   │       ├── DefaultScoreCalculator.java
│   │                           │   │   │   │       ├── FactorAggregator.java
│   │                           │   │   │   │       ├── LinearFactorAggregator.java
│   │                           │   │   │   │       ├── NameFactorCalculator.java
│   │                           │   │   │   │       ├── Score.java
│   │                           │   │   │   │       ├── ScoreCalculator.java
│   │                           │   │   │   │       └── TimeFactorCalculator.java
│   │                           │   │   └── usecases
│   │                           │   │       ├── DepositFundsUseCase.java
│   │                           │   │       ├── OpenAccountUseCase.java
│   │                           │   │       ├── ViewAccountUseCase.java
│   │                           │   │       └── WithdrawFundsUseCase.java
│   │                           │   ├── crud
│   │                           │   │   ├── common
│   │                           │   │   │   └── guards
│   │                           │   │   │       ├── BaseGuard.java
│   │                           │   │   │       ├── IntGuard.java
│   │                           │   │   │       ├── LongGuard.java
│   │                           │   │   │       ├── ObjectGuard.java
│   │                           │   │   │       └── StringGuard.java
│   │                           │   │   └── Guard.java
│   │                           │   └── usecases
│   │                           │       └── OpenAccountUseCase.java
│   │                           └── ports
│   │                               ├── driven
│   │                               │   ├── common
│   │                               │   │   └── Generator.java
│   │                               │   ├── events
│   │                               │   │   ├── AccountOpenedDto.java
│   │                               │   │   ├── EventDto.java
│   │                               │   │   ├── FundsDepositedDto.java
│   │                               │   │   ├── FundsWithdrawnDto.java
│   │                               │   ├── AccountIdGenerator.java
│   │                               │   ├── AccountNumberGenerator.java
│   │                               │   ├── BankAccountDto.java
│   │                               │   ├── BankAccountStorage.java
│   │                               │   ├── CustomerGateway.java
│   │                               │   ├── DateTimeService.java
│   │                               │   ├── EventBus.java
│   │                               │   └── NationalIdentityGateway.java
│   │                               └── driver
│   │                                   ├── accounts
│   │                                   │   ├── depositfunds
│   │                                   │   │   └── DepositFundsRequest.java
│   │                                   │   ├── openaccount
│   │                                   │   │   ├── OpenAccountRequest.java
│   │                                   │   │   ├── OpenAccountResponse.java
│   │                                   │   ├── viewaccount
│   │                                   │   │   ├── ViewAccountRequest.java
│   │                                   │   │   ├── ViewAccountResponse.java
│   │                                   │   ├── withdrawfunds
│   │                                   │       └── WithdrawFundsRequest.java
│   │                                   ├── exceptions
│   │                                   │   ├── ValidationException.java
│   │                                   │   └── ValidationMessages.java
│   │                                   └── VoidResponse.java
│   ├── test
│       └── java
│           └── com
│               └── optivem
│                   └── kata
│                       └── banking
│                           └── core
│                               ├── domain
│                               │   ├── AccountIdTest.java
│                               │   ├── BankAccountTest.java
│                               │   └── [other domain tests]
│                               ├── facade
│                               │   ├── DepositFundsUseCaseFacadeTest.java
│                               │   └── [other facade tests]
│                               ├── usecases
│                               │   ├── DepositFundsUseCaseTest.java
│                               │   ├── OpenAccountUseCaseTest.java
│                               │   └── [other use case tests]
│                               └── util
│                                   ├── TestUtility.java
│                                   └── [other utility tests]

