package com.example.springBoot.services;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    @Autowired
    private Binding directExchangeBinding;

    @Autowired
    private Queue myQueue1;

    @Autowired
    private DirectExchange directExchange;

    public void sendEmail(String email) {

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");
            Connection connection = factory.newConnection();

            if (connection != null) {
                Channel channel = connection.createChannel();
                channel.exchangeDeclare(directExchange.getName(), ExchangeTypes.DIRECT, true);
                channel.queueDeclare(myQueue1.getName(), true, false, false, null);
                channel.queueBind(myQueue1.getName(), directExchange.getName(), directExchangeBinding.getRoutingKey());
                channel.basicPublish(directExchange.getName(), directExchangeBinding.getRoutingKey(), null, email.getBytes());
                System.out.println(" Message Sent '" + email + "'");
                channel.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
