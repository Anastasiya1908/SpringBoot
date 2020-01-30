package com.example.springBoot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AppController {

    static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong COUNTER = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "email") String email) {

        LOGGER.info("Received email: " + email);
        return new Greeting(COUNTER.incrementAndGet(), String.format(TEMPLATE, email));

    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Empty mapping";
    }

    @RequestMapping("/emit/error")
    @ResponseBody
    String error() {
        LOGGER.info("Emit as error");
        rabbitTemplate.convertAndSend("error", "Error");
        return "Emit as error";
    }

    @RequestMapping("/emit/info")
    @ResponseBody
    String info() {
        LOGGER.info("Emit as info");
        rabbitTemplate.convertAndSend("info", "info");
        return "Emit as info";
    }

    @RequestMapping("/emit/warning")
    @ResponseBody
    String warning() {
        LOGGER.info("Emit as warning");
        rabbitTemplate.convertAndSend("warning", "Warning");
        return "Emit as warning";
    }
}
