package com.demo.kafkaDemo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public PublisherService(KafkaTemplate<String,Order> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }
    public void publishOrder(Order order){
        kafkaTemplate.send("order-sent",order);
    }
}
