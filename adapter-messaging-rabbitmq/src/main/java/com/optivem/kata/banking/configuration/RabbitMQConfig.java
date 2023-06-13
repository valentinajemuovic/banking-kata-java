package com.optivem.kata.banking.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitMQConfig {

    private static final String DEFAULT_EXCHANGE="defaultexchange-bk";
    private static final String DEFAULT_QUEUE="defaultqueue-bk";
    private static final String DEFAULT_ROUTING_KEY=DEFAULT_QUEUE;

   @Bean
   public CachingConnectionFactory connectionFactory() {
       CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
       cachingConnectionFactory.setUsername("admin");
       cachingConnectionFactory.setPassword("admin");
       return cachingConnectionFactory;
   }

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }


    /**
     * Initialize a default exchange and queue
     */

    @Bean
    public Exchange defaultExchange() {
        return ExchangeBuilder.directExchange(DEFAULT_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Queue defaultQueue() {
        return new Queue(DEFAULT_QUEUE, true, false, false);
    }
    @Bean
    public Binding defaultBinding(Exchange defaultExchange, Queue defaultQueue) {
        return BindingBuilder.bind(defaultQueue)
                .to(defaultExchange)
                .with(DEFAULT_ROUTING_KEY)
                .noargs();
    }

    @PostConstruct
    public void declareDefaultExchangeAndQueue() {
        Exchange exchange = defaultExchange();
        Queue queue = defaultQueue();
        Binding binding = defaultBinding(exchange, queue);
        amqpAdmin().declareExchange(exchange);
        amqpAdmin().declareQueue(queue);
        amqpAdmin().declareBinding(binding);
    }



   /**
    * Create DTO Serializer for rabbitmqTemplate
    */
   //this is to serialize pojo, therefore Jackson2MessageConverter from amqp library need to be binded with RabbitTemplate
   @Bean
   public MessageConverter jsonMessageConverter(){
       ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.registerModule(new JavaTimeModule());
       return new Jackson2JsonMessageConverter(objectMapper);
   }

   @Bean
   public RabbitTemplate rabbitAMQPTemplate(ConnectionFactory connectionFactory){
       RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
       rabbitTemplate.setMessageConverter(jsonMessageConverter());
       return rabbitTemplate;
   }
}
