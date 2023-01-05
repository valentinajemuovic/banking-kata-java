package com.optivem.kata.banking.adapters.restapi.spring;

import com.fasterxml.jackson.databind.JsonNode;
import org.antlr.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class RealTokenProvider implements TokenProvider {

    private final WebClient client;
    private final String tokenUri;
    private final MultiValueMap<String, String> tokenRequestData;

    public RealTokenProvider( /* @Autowired final WebTestClient client, */
            @Value("${token-uri}") final String tokenUri,
            @Value("${client-id}") final String clientId,
            @Value("${client-secret}") final String clientSecret) {

        this.client = WebClient.create(); // client;
        this.tokenUri = tokenUri;
        tokenRequestData = new LinkedMultiValueMap<>();

        tokenRequestData.add("client_id", clientId);
        tokenRequestData.add("client_secret", clientSecret);
        tokenRequestData.add("grant_type", "client_credentials");
    }

    public String getToken() {
        var token = client
                .post()
                .uri(tokenUri)
                .body(BodyInserters.fromFormData(tokenRequestData))
                .retrieve()
                // .exchange()
                // .returnResult(JsonNode.class)
                .bodyToMono(JsonNode.class)
                .block();

        return "Bearer " + token;

        /*
                .map(tokenResponse -> tokenResponse.get("access_token").textValue())
                .blockFirst(); */
    }
}
