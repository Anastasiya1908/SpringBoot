package com.example.springBoot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AppController {
    @Autowired
    EmailService emailService;

    static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public void Greeting(@RequestParam(value = "email") String email) {
        emailService.sendEmail(email);
        LOGGER.info("Received email: " + email);
    }
}
