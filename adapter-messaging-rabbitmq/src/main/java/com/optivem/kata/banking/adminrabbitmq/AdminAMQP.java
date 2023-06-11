package com.optivem.kata.banking.adminrabbitmq;

import com.optivem.kata.banking.configuration.RabbitMQConfig;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminAMQP {

    private RabbitAdmin rabbitAdmin;
    private RabbitTemplate rabbitTemplate;


    public AdminAMQP(RabbitAdmin rabbitAdmin, RabbitTemplate rabbitTemplate){
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitTemplate = rabbitTemplate;
    }


    public RabbitAdmin getRabbitAdmin(){
        return this.rabbitAdmin;
    }

    // public Exchange getExchangeByName(String exchangeName) {
    //         rabbitAdmin.getRabbitTemplate().getEx
    // }



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
