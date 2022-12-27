package com.optivem.kata.banking.adapters.driver.web.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final String jwkSetUri;

    public SecurityConfiguration(
        @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") String jwtSetUri) {
        this.jwkSetUri = jwtSetUri;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf()
            .disable()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt()
            .decoder(NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build());

        return http.build();
    }
}
