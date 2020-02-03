package com.example.SpringRabbitMQ;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    @Autowired
    private EmailService emailService;

    static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "query-1-1")
    public void worker1(String email) {
        LOGGER.info("Email : " + email);
    }

}
