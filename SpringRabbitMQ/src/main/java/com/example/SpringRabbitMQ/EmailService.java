package com.example.SpringRabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmail(String email) {
        rabbitTemplate.convertAndSend("exchange-1", "error", email);
    }


}