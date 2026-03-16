package com.example.order_service.service;

import com.example.order_service.entity.OrderEntity;
import com.example.order_service.event.OrderCreatedEvent;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.dto.request.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    public OrderEntity createOrder(OrderRequest orderRequest) {

        // Convert Request → Entity
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProductId(orderRequest.getProductId());
        orderEntity.setQuantity(orderRequest.getQuantity());
        orderEntity.setProductName(orderRequest.getProductName());
        orderEntity.setStatus("CREATED");

        // Save order in DB
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        // Create event
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getOrderId(),
                savedOrder.getProductId(),
                savedOrder.getProductName(),
                savedOrder.getQuantity(),
                savedOrder.getStatus()
        );

        // Send event to Kafka
        orderProducer.sendOrderEvent(event);

        return savedOrder;
    }
}