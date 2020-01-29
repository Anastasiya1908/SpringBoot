package com.example.springBoot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AppController {

    static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "email") String email) {

        LOGGER.info("Received email: " + email);
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, email));

    }

}
