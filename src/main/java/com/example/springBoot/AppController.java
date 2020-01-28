package com.example.springBoot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AppController {

    static final Logger logger = LoggerFactory.getLogger(AppController.class);

    private static final String template = "Hello, %s, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name") String name, @RequestParam(value="email") String email) {

        logger.info("Received email: " + email);
        return new Greeting(counter.incrementAndGet(), String.format(template , name, email));

    }

}
