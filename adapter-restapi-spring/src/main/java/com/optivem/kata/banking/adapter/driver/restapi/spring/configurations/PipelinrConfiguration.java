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
    public Pipeline pipeline(ObjectProvider<Command.Handler<? extends Command<?>, ?>> commandHandlers,
                             ObjectProvider<Notification.Handler<? extends Notification>> notificationHandlers,
                             ObjectProvider<Command.Middleware> middlewares) {
        return new Pipelinr()
                .with(() -> commandHandlers.stream().map(handler -> handler))
                .with(() -> notificationHandlers.stream().map(handler -> handler))
                .with(middlewares::orderedStream);
    }
}
