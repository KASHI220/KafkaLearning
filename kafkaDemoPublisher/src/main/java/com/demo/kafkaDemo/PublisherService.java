package com.demo.kafkaDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private static final Logger log =
            LoggerFactory.getLogger(PublisherService.class);

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public PublisherService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrder(Order order) {

        try {
            kafkaTemplate.send("order-sent", order)
                    .whenComplete((result, exception) -> {

                        if (exception != null) {

                            log.error("Failed to publish order: {}", order, exception);

                        } else {

                            log.info("Order published successfully: {}", order);

                            log.info("Topic: {}",
                                    result.getRecordMetadata().topic());

                            log.info("Partition: {}",
                                    result.getRecordMetadata().partition());

                            log.info("Offset: {}",
                                    result.getRecordMetadata().offset());
                        }
                    });

        } catch (Exception e) {

            log.error("Unexpected error while sending order: {}", order, e);
        }
    }
}