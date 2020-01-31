package com.example.springBoot;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmail(String email) {
        rabbitTemplate.convertAndSend("email", "Email");
    }

}

