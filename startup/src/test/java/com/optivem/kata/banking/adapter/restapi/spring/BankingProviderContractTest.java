package com.optivem.kata.banking.adapter.restapi.spring;

import an.awesome.pipelinr.Pipeline;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
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
// @ActiveProfiles({ "contract-test", ProfileNames.AdapterPersistenceJpa })
@ActiveProfiles({ ProfileNames.ADAPTER_PERSISTENCE_JPA, ProfileNames.ADAPTER_PERSISTENCE_REDIS, ProfileNames.ADAPTER_GENERATION_RANDOM, ProfileNames.ADAPTER_TIME_SYSTEM, ProfileNames.ADAPTER_THIRDPARTY_SIM, ProfileNames.ADAPTER_AUTH_NONE})
// @ContextConfiguration(classes = ContractTestConfiguration.class)
@Import(ContractTestConfiguration.class)
@Provider("banking-provider")
@PactFolder("../adapter-restapi-spring/build/pacts")
class BankingProviderContractTest {

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
        var testTarget = new HttpTestTarget("localhost", port);
        context.setTarget(testTarget);
    }

    @State("GET Bank Account: a bank account with the specified ID 999-999999-999 does not exist")
    public void toBankAccountNotExistState() {
        reset(pipeline);

        var accountNumber = "999-999999-999";
        var request = ViewAccountRequest.builder()
                        .accountNumber(accountNumber)
                                .build();

        when(pipeline.send(request)).thenThrow(new ValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST));
    }

    @State("GET Bank Account: a Bank Account with the specified ID ABC_001 already exists")
    public void toBankAccountABC101ExistState() {
        reset(pipeline);

        var request = ViewAccountRequest.builder()
                .accountNumber("ABC_001")
                .build();

        var response = ViewAccountResponse.builder()
                .accountNumber("ABC_001")
                .fullName("John Smith")
                .balance(20)
                .score(Score.C)
                .build();

        when(pipeline.send(request)).thenReturn(response);
    }
}