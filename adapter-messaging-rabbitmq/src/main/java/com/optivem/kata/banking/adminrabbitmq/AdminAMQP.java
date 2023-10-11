package com.optivem.kata.banking.adminrabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdminAMQP {
    private final RabbitAdmin rabbitAdmin;

    public AdminAMQP(RabbitAdmin rabbitAdmin, RabbitTemplate rabbitTemplate){
        this.rabbitAdmin = rabbitAdmin;
    }

    public RabbitAdmin getRabbitAdmin(){
        return this.rabbitAdmin;
    }

    public void createExchange(String exchangeName){
        Exchange exchange = ExchangeBuilder.directExchange(exchangeName)
                .durable(true)
                .build();

        rabbitAdmin.declareExchange(exchange);
    }

    public void createQueueAndBind(Exchange exchange) {

        // Create the queue
        Queue queue = new Queue("dataQueue", true, false, false);

        // Declare the binding
        Binding binding = BindingBuilder.bind(queue)
                .to(exchange)
                .with("routingKeyDataQueue")
                .noargs();

        // Bind the queue to the exchange
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
    }
}
