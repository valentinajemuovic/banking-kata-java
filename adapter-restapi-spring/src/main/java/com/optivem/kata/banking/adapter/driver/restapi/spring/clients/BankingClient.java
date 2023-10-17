package com.optivem.kata.banking.adapter.driver.restapi.spring.clients;

import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.ports.driver.accounts.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

// TODO: VC: This should be in a separate module, it's some consumer, e.g. loan
public class BankingClient {
    private static final String VIEW_ACCOUNT_PATH_FORMAT = "/bank-accounts/%s";
    private static final String OPEN_ACCOUNT_PATH_FORMAT = "/bank-accounts";

    private final String url;
    private final TokenProvider tokenProvider;

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
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(ViewAccountResponse.class)
                .block();

        if(viewAccountResponse == null) {
            return Optional.empty();
        }

        return Optional.of(viewAccountResponse);
    }

    public OpenAccountResponse openAccount(OpenAccountRequest request) {
        var client = WebClient.create(url);
        var token = tokenProvider.getToken();
        var responseSpec = client.post().uri(OPEN_ACCOUNT_PATH_FORMAT).header("Authorization", token).bodyValue(request).retrieve();

        return responseSpec
                .bodyToMono(OpenAccountResponse.class)
                .block();
    }
}
