package com.optivem.kata.banking.adapters.restapi.spring;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.Score;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "banking-provider", hostInterface = "localhost")
public class BankingConsumerContractTest {

    private static String EXISTING_ACCOUNT_NUMBER = "ABC_001";
    private static String NON_EXISTENT_ACCOUNT_NUMBER = "999-999999-999";

    private static String FULL_NAME = "John Smith";
    private static int BALANCE = 20;
    private static Score SCORE = Score.C;

    @Pact(consumer = "banking-consumer")
    public RequestResponsePact createPactForNonexistentAccountNumber(PactDslWithProvider builder) {
        var accountNumber = NON_EXISTENT_ACCOUNT_NUMBER;

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

    @Pact(consumer = "banking-consumer")
    public RequestResponsePact createPactForExistingAccountNumber(PactDslWithProvider builder) {
        var accountNumber = EXISTING_ACCOUNT_NUMBER;
        var body = new PactDslJsonBody()
                .stringValue("accountNumber", accountNumber)
                .stringType("fullName", FULL_NAME)
                .integerType("balance", BALANCE)
                .integerType("score", SCORE.ordinal());


        var pathFormat = "/bank-accounts/%s";
        var path = String.format(pathFormat, accountNumber);

        return builder.given("GET Bank Account: a Bank Account with the specified ID ABC_001 already exists")
                .uponReceiving("A request for Bank Account data")
                .path(path)
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForNonexistentAccountNumber")
    public void should_return_none_when_bank_account_not_exists(MockServer mockServer) {
        var client = createBankingClient(mockServer);
        var accountNumber = NON_EXISTENT_ACCOUNT_NUMBER;
        var response = client.viewAccount(accountNumber);

        assertThat(response).isEmpty();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForExistingAccountNumber")
    public void should_return_response_when_bank_account_exists(MockServer mockServer) {
        var client = createBankingClient(mockServer);
        var accountNumber = EXISTING_ACCOUNT_NUMBER;
        var response = client.viewAccount(accountNumber);

        var expectedResponse = ViewAccountResponse.builder()
            .accountNumber(EXISTING_ACCOUNT_NUMBER)
            .fullName(FULL_NAME)
            .balance(BALANCE)
            .score(SCORE)
            .build();

        assertThat(response).isNotEmpty();
        assertThat(response.get()).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    private BankingClient createBankingClient(MockServer mockServer) {
        var url = mockServer.getUrl();
        var tokenProvider = new FakeTokenProvider();
        return new BankingClient(url, tokenProvider);
    }
}



/*


public class RealNationalIdentityProviderConsumerContractTest {



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