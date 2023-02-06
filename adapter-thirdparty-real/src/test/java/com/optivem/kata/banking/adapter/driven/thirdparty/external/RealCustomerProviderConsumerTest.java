package com.optivem.kata.banking.adapter.driven.thirdparty.external;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.optivem.kata.banking.adapter.driven.base.CustomerProviderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "customer-provider", hostInterface = "localhost")
public class RealCustomerProviderConsumerTest extends CustomerProviderTest<RealCustomerProvider> {

    @BeforeEach
    public void init(MockServer mockServer) {
        var url = mockServer.getUrl();
        provider = new RealCustomerProvider(url);
    }

    @Pact(consumer = "banking-consumer")
    public RequestResponsePact createPactForBlacklistedCustomer(PactDslWithProvider builder) {
        var customerId = BLACKLISTED_ID;
        var body = new PactDslJsonBody()
                .stringType("id", customerId)
                .booleanType("blacklisted", true);

        var pathFormat = "/customers/%s";
        var path = String.format(pathFormat, customerId);

        return builder.given("GET User: customer ABC_1001 is blacklisted")
                .uponReceiving("A request for blacklisted customer ABC_1001")
                .path(path)
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Pact(consumer = "banking-consumer")
    public RequestResponsePact createPactForWhitelistedCustomer(PactDslWithProvider builder) {
        var customerId = WHITELISTED_ID;
        var body = new PactDslJsonBody()
                .stringType("id", customerId)
                .booleanType("blacklisted", false);

        var pathFormat = "/customers/%s";
        var path = String.format(pathFormat, customerId);

        return builder.given("GET User: customer ABC_1002 is whitelisted")
                .uponReceiving("A request for whitelisted customer ABC_1002")
                .path(path)
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Pact(consumer = "banking-consumer")
    public RequestResponsePact createPactForNonExistentCustomer(PactDslWithProvider builder) {
        var customerId = NON_EXISTENT_ID;

        var pathFormat = "/customers/%s";
        var path = String.format(pathFormat, customerId);

        return builder.given("GET User: customer DEF_1002 does not exist")
                .uponReceiving("A request for non-existent customer DEF_1002")
                .path(path)
                .method("GET")
                .willRespondWith()
                .status(404)
                .toPact();
    }


    @Override
    @Test
    @PactTestFor(pactMethod = "createPactForBlacklistedCustomer")
    public void should_return_true_when_user_is_blacklisted() {
        super.should_return_true_when_user_is_blacklisted();
    }

    @Override
    @Test
    @PactTestFor(pactMethod = "createPactForWhitelistedCustomer")
    public void should_return_true_when_user_is_whitelisted() {
        super.should_return_true_when_user_is_whitelisted();
    }

    @Override
    @Test
    @PactTestFor(pactMethod = "createPactForNonExistentCustomer")
    public void should_return_false_when_customer_not_exists() {
        super.should_return_false_when_customer_not_exists();
    }

}