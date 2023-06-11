package com.optivem.kata.banking;

import com.optivem.kata.banking.adminrabbitmq.AdminAMQP;
import com.optivem.kata.banking.configuration.RabbitMQConfig;
import com.optivem.kata.banking.core.ports.driven.EventBus;
import com.optivem.kata.banking.core.ports.driven.events.AccountOpenedDto;
import com.optivem.kata.banking.core.ports.driven.events.EventDto;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RabbitMQEventBus implements EventBus {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQEventBus(@Qualifier("rabbitAMQPTemplate") RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(EventDto event) {
        rabbitTemplate.convertAndSend("default-bankingkata-exchange","default-bankingkata-queue", event,message -> {
            message.getMessageProperties().setCorrelationId(LocalDateTime.now().toString());
            return message;
        });
    }
}