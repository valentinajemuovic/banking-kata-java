package com.optivem.kata.banking.adapters.thirdparty.external;

import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RealNationalIdentityProvider implements NationalIdentityProvider {
    @Override
    public boolean exists(String nationalIdentityNumber) {
        var client = WebClient.create("https://jsonplaceholder.typicode.com");
        var responseSpec = client.get().uri("users/ab").retrieve();

        var user = responseSpec
                .onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
                .bodyToMono(UserDto.class)
                .block();

        return user != null;
    }
}
