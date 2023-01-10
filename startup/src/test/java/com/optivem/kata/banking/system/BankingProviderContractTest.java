package com.optivem.kata.banking.system;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.optivem.kata.banking.BankingApplication;
import com.optivem.kata.banking.adapters.driven.ProfileNames;
import com.optivem.kata.banking.adapters.restapi.spring.clients.BankingClient;
import com.optivem.kata.banking.adapters.restapi.spring.clients.FakeTokenProvider;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = BankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ ProfileNames.AdapterPersistenceJpa, ProfileNames.AdapterGenerationRandom, ProfileNames.AdapterTimeSystem, ProfileNames.AdapterThirdpartySim, ProfileNames.AdapterAuthNone })
@ContextConfiguration
@Provider("banking-provider")
@PactFolder("../adapter-restapi-spring/build/pacts")
public class BankingProviderContractTest {

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

    }

    @State("GET Bank Account: a Bank Account with the specified ID ABC_001 already exists")
    public void toBankAccountABC101ExistState() {
        var url = "http://localhost:" + port;
        var tokenProvider = new FakeTokenProvider();
        var bankingClient = new BankingClient(url, tokenProvider);

        var openAccountRequest = OpenAccountRequest.builder()
                        .firstName("John")
                                .lastName("Smith")
                .nationalIdentityNumber("SIM_1")
                                        .build();

        var response = bankingClient.openAccount(openAccountRequest);
    }
}


/*

@Provider("national-identity-provider")
@PactFolder("build/pacts")
public class RealNationalIdentityProviderProviderContractTest {

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }


    @BeforeAll
    public static void start() {
        // TODO: Start the Spring Application
    }

    @SneakyThrows
    @BeforeEach
    void before(PactVerificationContext context) {
        var urlString = "https://jsonplaceholder.typicode.com";
        var url = new URL(urlString);
        var testTarget = HttpsTestTarget.fromUrl(url);
        context.setTarget(testTarget);
    }

    @State("GET User: a user with the specified ID already exists")
    public void toGetUserExistsState() {

    }
}

 */