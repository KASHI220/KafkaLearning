package com.kafkaDemoReciver;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderSubscriberService {

    @KafkaListener(topics = "order-sent", groupId = "order-group")
    public void consumeOrder(Order order) {

        System.out.println("Received order: " + order.toString());

        processOrder(order);
    }

    public void processOrder(Order order) {
        System.out.println("Processing order...");
    }
}
