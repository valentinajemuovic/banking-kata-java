package com.optivem.kata.banking.adapter.driver.restapi.spring;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.optivem.kata.banking.adapter.driver.restapi.spring.clients.BankingClient;
import com.optivem.kata.banking.adapter.driver.restapi.spring.clients.FakeTokenProvider;
import com.optivem.kata.banking.core.common.http.HttpMethodName;
import com.optivem.kata.banking.core.common.http.HttpStatusValue;
import com.optivem.kata.banking.core.internal.cleanarch.domain.scoring.Score;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = BankingConsumerContractTest.PROVIDER_NAME, hostInterface = "localhost")
public class BankingConsumerContractTest {

    public static final String PROVIDER_NAME = "banking-provider";
    private static final String CONSUMER_NAME = "banking-consumer";

    private static final String GET_BANK_ACCOUNT_PATH_FORMAT = "/bank-accounts/%s";

    private static final String ACCOUNT_NUMBER_FIELD = "accountNumber";
    private static final String FULL_NAME_FIELD = "fullName";
    private static final String BALANCE_FIELD = "balance";
    private static final String SCORE_FIELD = "score";

    private static final String EXISTING_ACCOUNT_NUMBER_VALUE = "ABC_001";
    private static final String NON_EXISTENT_ACCOUNT_NUMBER_VALUE = "999-999999-999";
    private static final String FULL_NAME_VALUE = "John Smith";
    private static final int BALANCE_VALUE = 20;
    private static final Score SCORE_VALUE = Score.C;

    @Pact(consumer = CONSUMER_NAME)
    public RequestResponsePact createPactForNonexistentAccountNumber(PactDslWithProvider builder) {

        var path = getPath(NON_EXISTENT_ACCOUNT_NUMBER_VALUE);

        return builder.given("GET Bank Account: a bank account with the specified ID 999-999999-999 does not exist")
                .uponReceiving("A request for Bank Account data")
                .path(path)
                .method(HttpMethodName.GET)
                .willRespondWith()
                .status(HttpStatusValue.NOT_FOUND)
                .toPact();
    }

    @Pact(consumer = CONSUMER_NAME)
    public RequestResponsePact createPactForExistingAccountNumber(PactDslWithProvider builder) {
        var accountNumber = EXISTING_ACCOUNT_NUMBER_VALUE;

        var body = new PactDslJsonBody()
                .stringValue(ACCOUNT_NUMBER_FIELD, accountNumber)
                .stringType(FULL_NAME_FIELD, FULL_NAME_VALUE)
                .integerType(BALANCE_FIELD, BALANCE_VALUE)
                .stringType(SCORE_FIELD, SCORE_VALUE.toString());

        var path = getPath(accountNumber);

        return builder.given("GET Bank Account: a Bank Account with the specified ID ABC_001 already exists")
                .uponReceiving("A request for Bank Account data")
                .path(path)
                .method(HttpMethodName.GET)
                .willRespondWith()
                .status(HttpStatusValue.OK)
                .body(body)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForNonexistentAccountNumber")
    void should_return_none_when_bank_account_not_exists(MockServer mockServer) {
        var client = createBankingClient(mockServer);
        var response = client.viewAccount(NON_EXISTENT_ACCOUNT_NUMBER_VALUE);

        assertThat(response).isEmpty();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForExistingAccountNumber")
    void should_return_response_when_bank_account_exists(MockServer mockServer) {
        var client = createBankingClient(mockServer);
        var response = client.viewAccount(EXISTING_ACCOUNT_NUMBER_VALUE);

        var expectedResponse = ViewAccountResponse.builder()
            .accountNumber(EXISTING_ACCOUNT_NUMBER_VALUE)
            .fullName(FULL_NAME_VALUE)
            .balance(BALANCE_VALUE)
            .score(SCORE_VALUE)
            .build();

        assertThat(response).isNotEmpty();
        assertThat(response.get()).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    private BankingClient createBankingClient(MockServer mockServer) {
        var url = mockServer.getUrl();
        var tokenProvider = new FakeTokenProvider();
        return new BankingClient(url, tokenProvider);
    }

    private static String getPath(String accountNumber) {
        return String.format(GET_BANK_ACCOUNT_PATH_FORMAT, accountNumber);
    }
}