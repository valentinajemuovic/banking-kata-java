package com.optivem.kata.banking;

import com.optivem.kata.banking.adapter.driven.base.ProfileNames;
import com.optivem.kata.banking.configuration.RabbitMQConfig;
import com.optivem.kata.banking.core.ports.driven.events.AccountOpenedDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@Profile(ProfileNames.ADAPTER_MESSAGING_RABBITMQ)
class RabbitMQEventBusTest {

    private RabbitMQEventBus rabbitMQEventBus;
    private RabbitTemplate rabbitTemplate;

    @Value("${default.exchange.name}") String exchangeName;
    @Value("${default.queue.name}") String queueName;
    @Value("${default.routingkey.name}") String routingKeyName;

    @BeforeEach
    public void init(){
        RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();

        this.rabbitTemplate = rabbitMQConfig.rabbitAMQPTemplate(rabbitMQConfig.connectionFactory());
        this.rabbitMQEventBus=new RabbitMQEventBus(this.rabbitTemplate,exchangeName,queueName,routingKeyName);

    }

    private static Stream<Arguments> should_return_AccountOpenedDto_event() {
        return Stream.of(Arguments.of(new AccountOpenedDto(LocalDateTime.now(),35535L,"Sam","Brook",1)),
                Arguments.of(new AccountOpenedDto(LocalDateTime.now(),35535L,"Josh","Long",1)));
    }


    @ParameterizedTest
    @MethodSource("should_return_AccountOpenedDto_event")
    public void should_send_dto_to_default_queue(AccountOpenedDto accountOpenedDto){
        rabbitMQEventBus.publish(accountOpenedDto);
    }

    @Test
    public void should_consume_message_from_queue(){
            Object message = rabbitTemplate.receiveAndConvert("default-bankingkata-queue");
            if (message != null) {
                if(message instanceof AccountOpenedDto){
                    AccountOpenedDto accountOpenedDto = (AccountOpenedDto) message;
                    System.out.println(accountOpenedDto.toString());
                }
            } else {
                System.out.println("No messages in the queue.");
            }
    }
}