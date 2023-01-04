package com.optivem.kata.banking.adapters.thirdparty.external;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "national-identity-provider", hostInterface = "localhost")
public class RealNationalIdentityProviderContractTest {

    @Pact(consumer = "banking-consumer")
    public RequestResponsePact createPactForExistingUserId(PactDslWithProvider builder) {
        var userId = "USR_002";
        var body = new PactDslJsonBody()
                .stringType("id", userId);

        var pathFormat = "/users/%s";
        var path = String.format(pathFormat, userId);

        return builder.given("GET User: a user with the specified ID already exists")
                .uponReceiving("A request for User data")
                .path(path)
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Pact(consumer = "banking-consumer")
    public RequestResponsePact createPactForNonexistentUserId(PactDslWithProvider builder) {
        var userId = "USR_999";

        var pathFormat = "/users/%s";
        var path = String.format(pathFormat, userId);

        return builder.given("GET User: a user with the specified ID already exists")
                .uponReceiving("A request for User data")
                .path(path)
                .method("GET")
                .willRespondWith()
                .status(404)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForExistingUserId")
    public void should_return_true_when_user_exists(MockServer mockServer) {
        var url = mockServer.getUrl();
        var provider = new RealNationalIdentityProvider(url);
        var nationalIdentityNumber = "USR_002";
        var exists = provider.exists(nationalIdentityNumber);
        assertThat(exists).isTrue();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForNonexistentUserId")
    public void should_return_false_when_user_not_exists(MockServer mockServer) {
        var url = mockServer.getUrl();
        var provider = new RealNationalIdentityProvider(url);
        var nationalIdentityNumber = "USR_999";
        var exists = provider.exists(nationalIdentityNumber);
        assertThat(exists).isFalse();
    }
}
