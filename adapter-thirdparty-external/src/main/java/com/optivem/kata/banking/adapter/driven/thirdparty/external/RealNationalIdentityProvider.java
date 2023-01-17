package com.optivem.kata.banking.adapter.driven.thirdparty.external;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.NationalIdentityProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Profile(ProfileNames.AdapterThirdpartyReal)
public class RealNationalIdentityProvider implements NationalIdentityProvider {
    private static final String URL = "https://jsonplaceholder.typicode.com"; // TODO: Move into configuration
    private static final String PATH = "users/%s";

    private final String url;

    public RealNationalIdentityProvider(String url) {
        this.url = url;
    }

    public RealNationalIdentityProvider() {
        this(URL); // TODO: VC: Should be injected from configuration
    }

    @Override
    public boolean exists(String nationalIdentityNumber) {
        var client = WebClient.create(url);
        var path = getPath(nationalIdentityNumber);
        var responseSpec = client.get().uri(path).retrieve();

        var user = responseSpec
                .onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
                .bodyToMono(UserDto.class)
                .block();

        return user != null && user.getId() != null;
    }

    private String getPath(String nationalIdentityNumber) {
        return String.format(PATH, nationalIdentityNumber);
    }
}
