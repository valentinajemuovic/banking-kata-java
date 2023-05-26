package com.optivem.kata.banking.adapter.driven.persistence.redis.configurations;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@Profile(ProfileNames.ADAPTER_PERSISTENCE_REDIS)
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        var redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(password);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<byte[], byte[]> redisTemplate() {
        var template = new RedisTemplate<byte[], byte[]>();
        template.setConnectionFactory(lettuceConnectionFactory());
        return template;
    }
}
