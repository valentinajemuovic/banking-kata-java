package com.optivem.kata.banking.adapter.driver.restapi.spring.configurations;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelinrConfiguration {
    @Bean
    public Pipeline pipeline(ObjectProvider<Command.Handler> commandHandlers,
                             ObjectProvider<Notification.Handler> notificationHandlers,
                             ObjectProvider<Command.Middleware> middlewares) {
        return new Pipelinr()
                .with(commandHandlers::stream)
                .with(notificationHandlers::stream)
                .with(middlewares::orderedStream);
    }
}
