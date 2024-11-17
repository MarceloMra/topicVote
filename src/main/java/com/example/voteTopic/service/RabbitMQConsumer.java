package com.example.voteTopic.service;

import com.example.voteTopic.configuration.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    private Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(String message) {
        logger.info("Received message: " + message);
    }
}
