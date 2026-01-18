package com.example.loyaltyapi.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void onOrderCreated(String message) {
        System.out.println(message);
    }
}
