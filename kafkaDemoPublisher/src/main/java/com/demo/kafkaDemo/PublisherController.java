package com.demo.kafkaDemo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {
    private final PublisherService publisherService;
    public PublisherController(PublisherService publisherService){
        this.publisherService=publisherService;
    }
    @PostMapping("/api/publish")
    public String  sendObject(@RequestBody Order order){
        publisherService.publishOrder(order);
        return "order sent successfully";
    }

}
