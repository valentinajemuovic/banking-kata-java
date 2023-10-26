package com.optivem.kata.banking.adminrabbitmq;

import lombok.Getter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AdminAMQP {
    private final RabbitAdmin rabbitAdmin;

    public AdminAMQP(RabbitAdmin rabbitAdmin){
        this.rabbitAdmin = rabbitAdmin;
    }

    public void createExchange(String exchangeName){
        var exchange = ExchangeBuilder.directExchange(exchangeName)
                .durable(true)
                .build();

        rabbitAdmin.declareExchange(exchange);
    }

    public void createQueueAndBind(Exchange exchange) {
        var queue = new Queue("dataQueue", true, false, false);

        var binding = BindingBuilder.bind(queue)
                .to(exchange)
                .with("routingKeyDataQueue")
                .noargs();

        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
    }
}
