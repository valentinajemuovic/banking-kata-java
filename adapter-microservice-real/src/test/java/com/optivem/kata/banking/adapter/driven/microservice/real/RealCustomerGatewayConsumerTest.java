package com.optivem.kata.banking.adapter.driven.microservice.real;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.optivem.kata.banking.adapter.driven.base.CustomerGatewayTest;
import com.optivem.kata.banking.core.common.http.HttpHost;
import com.optivem.kata.banking.core.common.http.HttpMethodName;
import com.optivem.kata.banking.core.common.http.HttpStatusValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = RealCustomerGatewayConsumerTest.PROVIDER_NAME, hostInterface = HttpHost.LOCALHOST)
public class RealCustomerGatewayConsumerTest extends CustomerGatewayTest<RealCustomerGateway> {

    public static final String PROVIDER_NAME = "customer-provider";
    private static final String CONSUMER_NAME = "banking-consumer";

    private static final String GET_CUSTOMER_PATH_FORMAT = "/customers/%s";

    private static final String ID_FIELD = "id";
    private static final String BLACKLISTED_FIELD = "blacklisted";

    private static final boolean BLACKLISTED_VALUE = true;
    private static final boolean WHITELISTED_VALUE = false;

    @BeforeEach
    public void init(MockServer mockServer) {
        var url = mockServer.getUrl();
        provider = new RealCustomerGateway(url);
    }

    @Pact(consumer = CONSUMER_NAME)
    public RequestResponsePact createPactForBlacklistedCustomer(PactDslWithProvider builder) {
        var customerId = BLACKLISTED_ID;
        var body = new PactDslJsonBody()
                .stringType(ID_FIELD, customerId)
                .booleanType(BLACKLISTED_FIELD, BLACKLISTED_VALUE);

        var path = getPath(customerId);

        return builder.given("GET User: customer ABC_1001 is blacklisted")
                .uponReceiving("A request for blacklisted customer ABC_1001")
                .path(path)
                .method(HttpMethodName.GET)
                .willRespondWith()
                .status(HttpStatusValue.OK)
                .body(body)
                .toPact();
    }

    @Pact(consumer = CONSUMER_NAME)
    public RequestResponsePact createPactForWhitelistedCustomer(PactDslWithProvider builder) {
        var customerId = WHITELISTED_ID;
        var body = new PactDslJsonBody()
                .stringType(ID_FIELD, customerId)
                .booleanType(BLACKLISTED_FIELD, WHITELISTED_VALUE);

        var path = getPath(customerId);

        return builder.given("GET User: customer ABC_1002 is whitelisted")
                .uponReceiving("A request for whitelisted customer ABC_1002")
                .path(path)
                .method(HttpMethodName.GET)
                .willRespondWith()
                .status(HttpStatusValue.OK)
                .body(body)
                .toPact();
    }

    @Pact(consumer = CONSUMER_NAME)
    public RequestResponsePact createPactForNonExistentCustomer(PactDslWithProvider builder) {
        var customerId = NON_EXISTENT_ID;

        var path = getPath(customerId);

        return builder.given("GET User: customer DEF_1002 does not exist")
                .uponReceiving("A request for non-existent customer DEF_1002")
                .path(path)
                .method(HttpMethodName.GET)
                .willRespondWith()
                .status(HttpStatusValue.NOT_FOUND)
                .toPact();
    }

    private static String getPath(String customerId) {
        return String.format(GET_CUSTOMER_PATH_FORMAT, customerId);
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