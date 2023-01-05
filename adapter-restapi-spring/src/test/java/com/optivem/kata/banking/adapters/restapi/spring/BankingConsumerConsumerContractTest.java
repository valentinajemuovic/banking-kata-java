package com.optivem.kata.banking.adapters.restapi.spring;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "banking-provider", hostInterface = "localhost")
public class BankingConsumerConsumerContractTest {

    @Pact(consumer = "banking-consumer")
    public RequestResponsePact createPactForNonexistentAccountNumber(PactDslWithProvider builder) {
        var accountNumber = "999-999999-999";

        var pathFormat = "/bank-accounts/%s";
        var path = String.format(pathFormat, accountNumber);

        return builder.given("GET Bank Account: a bank account with the specified ID already exists")
                .uponReceiving("A request for Bank Account data")
                .path(path)
                .method("GET")
                .willRespondWith()
                .status(404)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForNonexistentAccountNumber")
    public void should_return_none_when_bank_account_not_exists(MockServer mockServer) {
        var url = mockServer.getUrl();
        var client = WebClient.create(url);
        var path = url + "/bank-accounts/999-999999-999";
        var responseSpec = client.get().uri(path).retrieve();

        var bankAccount = responseSpec
                .onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
                .bodyToMono(ViewAccountResponse.class)
                .block();

        assertThat(bankAccount).isNull();
    }
}



/*


public class RealNationalIdentityProviderConsumerContractTest {

    private static int EXISTING_ID = 2;
    private static int INVALID_ID = 99;

    @Pact(consumer = "banking-consumer")
    public RequestResponsePact createPactForExistingUserId(PactDslWithProvider builder) {
        var userId = EXISTING_ID;
        var body = new PactDslJsonBody()
                .numberType("id", userId);

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




    @Test
    @PactTestFor(pactMethod = "createPactForExistingUserId")
    public void should_return_true_when_user_exists(MockServer mockServer) {
        var url = mockServer.getUrl();
        var provider = new RealNationalIdentityProvider(url);
        var nationalIdentityNumber = String.valueOf(EXISTING_ID);
        var exists = provider.exists(nationalIdentityNumber);
        assertThat(exists).isTrue();
    }


}

 */