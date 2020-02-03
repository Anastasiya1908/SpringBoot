
package com.example.springBoot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    Logger logger = LoggerFactory.getLogger(getClass());


    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        return new SimpleMessageListenerContainer(connectionFactory);
    }

    @Value("${spring.rabbitmq.template.default-receive-queue}")
    private String QueueName;

    @Bean
    public Queue myQueue1() {
        return new Queue(QueueName, true);
    }

    @Value("${spring.rabbitmq.template.exchange}")
    private String ExchangeName;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(ExchangeName);
    }

    @Bean
    public Binding errorBinding1() {
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("error");
    }

}

