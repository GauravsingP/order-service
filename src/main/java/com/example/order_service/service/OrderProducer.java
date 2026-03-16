package com.example.order_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.order_service.event.OrderCreatedEvent;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    // Constructor Injection
    public OrderProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderCreatedEvent event) {
        // Convert key to String explicitly
        kafkaTemplate.send("order-created", String.valueOf(event.getOrderId()), event);
        System.out.println("Order event sent to Kafka: " + event);
    }
}