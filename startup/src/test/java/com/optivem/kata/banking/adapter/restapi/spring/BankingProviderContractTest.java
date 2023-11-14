package com.optivem.kata.banking.adapter.restapi.spring;

import an.awesome.pipelinr.Pipeline;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.common.http.HttpHost;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.Score;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationException;
import com.optivem.kata.banking.core.ports.driver.exceptions.ValidationMessages;
import com.optivem.kata.banking.BankingApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ ProfileNames.ADAPTER_PERSISTENCE_JPA, ProfileNames.ADAPTER_PERSISTENCE_REDIS, ProfileNames.ADAPTER_GENERATION_RANDOM, ProfileNames.ADAPTER_TIME_SYSTEM, ProfileNames.ADAPTER_MICROSERVICE_SIM, ProfileNames.ADAPTER_THIRDPARTY_SIM, ProfileNames.ADAPTER_AUTH_NONE})
@Import(ContractTestConfiguration.class)
@Provider("banking-provider")
@PactFolder("../adapter-restapi-spring/build/pacts") // TODO: Use Pact Broker
class BankingProviderContractTest {

    private static final String EXISTING_ACCOUNT_NUMBER_VALUE = "ABC_001";
    private static final String NON_EXISTENT_ACCOUNT_NUMBER_VALUE = "999-999999-999";
    private static final String FULL_NAME_VALUE = "John Smith";
    private static final int BALANCE_VALUE = 20;
    private static final Score SCORE_VALUE = Score.C;

    @MockBean
    private Pipeline pipeline;

    @LocalServerPort
    private int port;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        var testTarget = new HttpTestTarget(HttpHost.LOCALHOST, port);
        context.setTarget(testTarget);
    }

    @State("GET Bank Account: a bank account with the specified ID 999-999999-999 does not exist")
    public void toBankAccountNotExistState() {
        reset(pipeline);

        var request = ViewAccountRequest.builder()
                        .accountNumber(NON_EXISTENT_ACCOUNT_NUMBER_VALUE)
                                .build();

        var validationException = new ValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST);
        when(pipeline.send(request)).thenThrow(validationException);
    }

    @State("GET Bank Account: a Bank Account with the specified ID ABC_001 already exists")
    public void toBankAccountABC101ExistState() {
        reset(pipeline);

        var accountNumber = EXISTING_ACCOUNT_NUMBER_VALUE;

        var request = ViewAccountRequest.builder()
                .accountNumber(accountNumber)
                .build();

        var response = ViewAccountResponse.builder()
                .accountNumber(accountNumber)
                .fullName(FULL_NAME_VALUE)
                .balance(BALANCE_VALUE)
                .score(SCORE_VALUE)
                .build();

        when(pipeline.send(request)).thenReturn(response);
    }
}