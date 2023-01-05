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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(classes = BankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ "test", ProfileNames.AdapterPersistenceJpa, ProfileNames.AdapterGenerationRandom, ProfileNames.AdapterTimeSystem })
@ContextConfiguration
@Provider("banking-provider")
@PactFolder("C:\\Users\\valen\\GitHub\\valentinacupac\\banking-kata-java\\adapter-restapi-spring\\build\\pacts")
@Disabled
public class BankingProviderContractTest {

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }


    @BeforeAll
    public static void start() {
        SpringApplication.run(BankingApplication.class);
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        var testTarget = new HttpTestTarget();
        context.setTarget(testTarget);
    }

    @State("GET Bank Account: a bank account with the specified ID already exists")
    public void toBankAccountExistState() {

    }

    @State("GET Bank Account: a Bank Account with the specified ID ABC_001 already exists")
    public void toBankAccountABC101ExistState() {

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