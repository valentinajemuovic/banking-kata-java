package com.optivem.kata.banking;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile(ProfileNames.ADAPTER_MESSAGING_RABBITMQ)
public class RabbitMQEventBus implements EventBus {
    private final RabbitTemplate rabbitTemplate;

    private final String defaultExchangeName;
    private final String defaultRoutingKeyQueue;

    @Autowired
    public RabbitMQEventBus(@Qualifier("rabbitAMQPTemplate") RabbitTemplate rabbitTemplate,
                            @Value("${default.exchange.name}") String exchangeName,
                            @Value("${default.routingkey.name}") String routingKeyName){

        this.rabbitTemplate = rabbitTemplate;
        this.defaultExchangeName = exchangeName;
        this.defaultRoutingKeyQueue = routingKeyName;
    }

    @Override
    public void publish(EventDto event) {
        rabbitTemplate.convertAndSend(defaultExchangeName,defaultRoutingKeyQueue, event,message -> {
            message.getMessageProperties().setCorrelationId(LocalDateTime.now().toString());
            return message;
        });
    }
}