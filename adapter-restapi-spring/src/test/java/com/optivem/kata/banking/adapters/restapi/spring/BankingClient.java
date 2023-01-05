package com.optivem.kata.banking.adapters.restapi.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

// TODO: VC: This should be in a separate module, it's some consumer, e.g. loan
public class BankingClient {
    private static final String VIEW_ACCOUNT_PATH_FORMAT = "/bank-accounts/%s";

    private final String url;
    private TokenProvider tokenProvider;

    public BankingClient(String url, TokenProvider tokenProvider) {
        this.url = url;
        this.tokenProvider = tokenProvider;
    }

    public Optional<ViewAccountResponse> viewAccount(String accountNumber) {
        var client = WebClient.create(url);
        var path = String.format(VIEW_ACCOUNT_PATH_FORMAT, accountNumber);
        var token = tokenProvider.getToken();
        var responseSpec = client.get().uri(path).header("Authorization", token).retrieve();

        var viewAccountResponse = responseSpec
                .onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
                .bodyToMono(ViewAccountResponse.class)
                .block();

        if(viewAccountResponse == null) {
            return Optional.empty();
        }

        return Optional.of(viewAccountResponse);
    }
}
