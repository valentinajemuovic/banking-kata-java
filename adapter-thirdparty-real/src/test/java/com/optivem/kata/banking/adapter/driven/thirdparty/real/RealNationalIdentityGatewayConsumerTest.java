package com.optivem.kata.banking.adapter.driven.thirdparty.real;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.optivem.kata.banking.adapter.driven.base.NationalIdentityGatewayTest;
import com.optivem.kata.banking.core.common.http.HttpHost;
import com.optivem.kata.banking.core.common.http.HttpMethodName;
import com.optivem.kata.banking.core.common.http.HttpStatusValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = RealNationalIdentityGatewayConsumerTest.PROVIDER_NAME, hostInterface = HttpHost.LOCALHOST)
public class RealNationalIdentityGatewayConsumerTest extends NationalIdentityGatewayTest<RealNationalIdentityGateway> {

    public static final String PROVIDER_NAME = "national-identity-provider";

    private static final String CONSUMER_NAME = "banking-consumer";

    private static final String GET_USER_PATH_FORMAT = "/users/%s";

    private static final String ID_FIELD = "id";
    private RealNationalIdentityGateway provider;

    @BeforeEach
    public void init(MockServer mockServer) {
        var url = mockServer.getUrl();
        provider = provider;
    }

    @Pact(consumer = CONSUMER_NAME)
    public RequestResponsePact createPactForExistingUserId(PactDslWithProvider builder) {
        var userId = Integer.parseInt(EXISTING_ID);

        var body = new PactDslJsonBody()
                .numberType(ID_FIELD, userId);

        var path = getPath(userId);

        return builder.given("GET User: a user with the specified ID already exists")
                .uponReceiving("A request for User data")
                .path(path)
                .method(HttpMethodName.GET)
                .willRespondWith()
                .status(HttpStatusValue.OK)
                .body(body)
                .toPact();
    }

    @Pact(consumer = CONSUMER_NAME)
    public RequestResponsePact createPactForNonexistentUserId(PactDslWithProvider builder) {
        var userId = Integer.parseInt(INVALID_ID);

        var path = getPath(userId);

        return builder.given("GET User: a user with the specified ID already exists")
                .uponReceiving("A request for User data")
                .path(path)
                .method(HttpMethodName.GET)
                .willRespondWith()
                .status(HttpStatusValue.NOT_FOUND)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForExistingUserId")
    public void should_return_true_when_user_exists() {
        super.should_return_true_when_user_exists();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForNonexistentUserId")
    public void should_return_false_when_user_not_exists() {
        super.should_return_false_when_user_not_exists();
    }

    private static String getPath(int userId) {
        return String.format(GET_USER_PATH_FORMAT, userId);
    }
}
