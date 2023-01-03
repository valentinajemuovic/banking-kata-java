package com.optivem.kata.banking.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.optivem.kata.banking.BankingApplication;
import com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import static com.optivem.kata.banking.core.common.builders.requests.OpenAccountRequestBuilder.openAccountRequest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ "test", "adapter-persistence-jpa", "adapter-generation-random", "adapter-time-system" }) // TODO: Referencing ProfileNames results in build error
@ContextConfiguration
class BankAccountControllerSystemTest {

    private final WebTestClient client;
    private final String tokenUri;
    private final MultiValueMap<String, String> tokenRequestData;

    public BankAccountControllerSystemTest(
        @Autowired final WebTestClient client,
        @Value("${token-uri}") final String tokenUri,
        @Value("${client-id}") final String clientId,
        @Value("${client-secret}") final String clientSecret) {

        this.client = client;
        this.tokenUri = tokenUri;
        tokenRequestData = new LinkedMultiValueMap<>();

        tokenRequestData.add("client_id", clientId);
        tokenRequestData.add("client_secret", clientSecret);
        tokenRequestData.add("grant_type", "client_credentials");
    }

    private String getToken() {
        return "Bearer "
            + client
            .post()
            .uri(tokenUri)
            .body(BodyInserters.fromFormData(tokenRequestData))
            .exchange()
            .returnResult(JsonNode.class)
            .getResponseBody()
            .map(tokenResponse -> tokenResponse.get("access_token").textValue())
            .blockFirst();
    }

    @Test
    void should_open_account_given_valid_request() {
        var request = OpenAccountRequestBuilder.openAccountRequest().build();

        final var response =
            client
                .post()
                .uri("bank-accounts")
                .header("Authorization", getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.CREATED)
                .expectBody(OpenAccountResponse.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getAccountNumber()).isNotBlank();

        final var accountNumber = response.getAccountNumber();

        final var viewResponse =
            client
                .get()
                .uri("bank-accounts/" + accountNumber)
                .header("Authorization", getToken())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK)
                .expectBody(ViewAccountResponse.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(viewResponse).isNotNull();
        Assertions.assertThat(viewResponse.getAccountNumber()).isEqualTo(accountNumber);
        Assertions.assertThat(viewResponse.getFullName()).isNotBlank();
        Assertions.assertThat(viewResponse.getBalance()).isNotNegative();
    }
}
