package com.optivem.kata.banking.adminrabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdminAMQPTest {

    @Mock
    private RabbitAdmin rabbitAdmin;

    @InjectMocks
    private AdminAMQP adminAMQP;

    @Test
    void createExchange_ShouldCallDeclareExchange() {
        var exchangeName = "testExchange";
        adminAMQP.createExchange(exchangeName);
        verify(rabbitAdmin).declareExchange(any(DirectExchange.class));
    }

    @Test
    void createQueueAndBind_ShouldCallDeclareQueueAndDeclareBinding() {
        var exchange = new DirectExchange("testExchange");
        adminAMQP.createQueueAndBind(exchange);
        verify(rabbitAdmin).declareQueue(any());
        verify(rabbitAdmin).declareBinding(any());
    }
}
