package com.optivem.kata.banking.adapters.thirdparty.external;

import com.optivem.kata.banking.adapters.driven.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.CustomerProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Profile(ProfileNames.AdapterThirdpartyReal)
public class RealCustomerProvider implements CustomerProvider {

    private static final String PATH = "customers/%s";

    private final String uri;

    public RealCustomerProvider(@Value("${customer-provider-uri}") final String uri) {
        this.uri = uri;
    }

    @Override
    public boolean isBlacklisted(String nationalIdentityNumber) {
        var client = WebClient.create(uri);
        var path = getPath(nationalIdentityNumber);
        var responseSpec = client.get().uri(path).retrieve();

        var customerDto = responseSpec
                // .onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
                .bodyToMono(CustomerDto.class)
                .block();

        return customerDto.isBlacklisted();
    }


    private String getPath(String nationalIdentityNumber) {
        return String.format(PATH, nationalIdentityNumber);
    }
}