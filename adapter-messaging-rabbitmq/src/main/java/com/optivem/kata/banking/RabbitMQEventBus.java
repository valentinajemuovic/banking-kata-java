package com.optivem.kata.banking;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.adminrabbitmq.AdminAMQP;
import com.optivem.kata.banking.configuration.RabbitMQConfig;
import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.AccountOpenedDto;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
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

    private RabbitTemplate rabbitTemplate;

    private final String DEFAULT_EXCHANGE_NAME;
    private final String DEFAULT_QUEUE_NAME;
    private final String DEFAULT_ROUTINGKEY_QUEUE;


    @Autowired
    public RabbitMQEventBus(@Qualifier("rabbitAMQPTemplate") RabbitTemplate rabbitTemplate,
                            @Value("${default.exchange.name}") String exchangeName,
                            @Value("${default.queue.name}") String queueName,
                            @Value("${default.routingkey.name}") String routingKeyName){

        this.rabbitTemplate = rabbitTemplate;
        this.DEFAULT_EXCHANGE_NAME = exchangeName;
        this.DEFAULT_QUEUE_NAME = queueName;
        this.DEFAULT_ROUTINGKEY_QUEUE = routingKeyName;

    }

    @Override
    public void publish(EventDto event) {
        rabbitTemplate.convertAndSend(DEFAULT_EXCHANGE_NAME,DEFAULT_ROUTINGKEY_QUEUE, event,message -> {
            message.getMessageProperties().setCorrelationId(LocalDateTime.now().toString());
            return message;
        });
    }
}