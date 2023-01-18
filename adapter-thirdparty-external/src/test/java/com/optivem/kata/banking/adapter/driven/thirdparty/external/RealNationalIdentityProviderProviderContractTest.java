package com.optivem.kata.banking.adapter.driven.thirdparty.external;


import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpsTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URL;

// TODO: Where to place this file?
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
