package com.optivem.kata.banking.adapters.restapi.spring;

import com.optivem.kata.banking.core.ports.driver.accounts.viewaccount.ViewAccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

// TODO: VC: This should be in a separate module, it's some consumer, e.g. loan
public class BankingClient {

    private static final String VIEW_ACCOUNT_PATH_FORMAT = "/bank-accounts/%s";

    private final String url;

    public BankingClient(String url) {
        this.url = url;
    }

    public Optional<ViewAccountResponse> viewAccount(String accountNumber) {
        var client = WebClient.create(url);
        var path = String.format(VIEW_ACCOUNT_PATH_FORMAT, accountNumber);
        var responseSpec = client.get().uri(path).retrieve();

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
