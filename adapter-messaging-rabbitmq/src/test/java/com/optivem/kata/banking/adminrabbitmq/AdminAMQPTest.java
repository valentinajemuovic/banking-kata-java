package com.optivem.kata.banking.adminrabbitmq;

import com.optivem.kata.banking.adminrabbitmq.AdminAMQP;
import com.optivem.kata.banking.configuration.RabbitMQConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
class AdminAMQPTest {

    private AdminAMQP adminAMQP;

    @BeforeEach
    public void init(){

        RabbitAdmin rabbitAdmin = new RabbitAdmin(new RabbitMQConfig().connectionFactory());
        this.adminAMQP = new AdminAMQP(rabbitAdmin, new RabbitTemplate());
    }

    @Test
    @Order(1)
    public void should_create_an_exchange_when_calling_create_exchange(){

        adminAMQP.createExchange("testExchange2");
        //todo - do some assert
    }

    @Test
    @Order(2)
    public void should_create_queue_and_bind_when_calling_createQueueAndBind(){
        Exchange exchange = ExchangeBuilder.directExchange("testExchange2")
                .durable(true)
                .build();

        adminAMQP.createQueueAndBind(exchange);

        // var dq = adminAMQP.getRabbitAdmin().getQueueInfo("dataQueue");
        // var test1 = adminAMQP.getRabbitAdmin().getQueueProperties("dataQueue");

        //todo - do some assert

    }

}