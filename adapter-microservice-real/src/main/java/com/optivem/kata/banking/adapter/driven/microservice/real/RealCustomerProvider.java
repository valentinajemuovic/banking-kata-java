package com.optivem.kata.banking.adapter.driven.microservice.real;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.CustomerProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Profile(ProfileNames.ADAPTER_THIRDPARTY_REAL)
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
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(CustomerDto.class)
                .block();

        if(customerDto == null) {
            return false;
        }

        return customerDto.isBlacklisted();
    }


    private String getPath(String nationalIdentityNumber) {
        return String.format(PATH, nationalIdentityNumber);
    }
}