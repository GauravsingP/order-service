package com.example.order_service.controller;

import com.example.order_service.client.InventoryClient;
import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.entity.OrderEntity;
import com.example.order_service.event.OrderCreatedEvent;
import com.example.order_service.service.OrderProducer;
import com.example.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final RestTemplate restTemplate;
    private final InventoryClient inventoryClient;
    private final OrderProducer orderProducer;
    private final OrderService orderService;

    public OrderController(InventoryClient inventoryClient,
                           RestTemplate restTemplate,
                           OrderProducer orderProducer, OrderService orderService) {

        this.inventoryClient = inventoryClient;
        this.restTemplate = restTemplate;
        this.orderProducer = orderProducer;
        this.orderService = orderService;
    }

    // Hellowrld
    @GetMapping("/hello")
    public String helloOrder()
    {
        return "Hello World";
    }

    //Communication with Inventory service using rest template
    @GetMapping("/inventory-check")
    @CircuitBreaker(name = "inventory-service", fallbackMethod = "fallbackMethod")
    public String inventoryCheck()
    {
        String response =restTemplate.getForObject("http://inventory-service/inventory/check-inventory", String.class);
        return "Order service calling inventory service and : " + response;
    }

    public String fallbackMethod(Exception exception)
    {
        return "Inventory service is not available plz retry";
    }

    //Communication with Inventory service using open feign client and circuit breaker is also implemented using feign to show fallback response if inventory service is not available or failing
    @GetMapping("/check-stock/{productId}")
    public String checkStock(@PathVariable String productId)
    {
        String response = inventoryClient.checkStock(productId);
        return "Order service calling inventory service by using open feign and : " + response;
    }

    @PostMapping("/createOrderDummy")
    public String createOrderUsingKafka(@RequestBody OrderCreatedEvent event) {

        orderProducer.sendOrderEvent(event);

        return "Order event sent to Kafka successfully!";
    }

    @PostMapping("/createOrder")
    public OrderEntity createOrder(@RequestBody OrderRequest order) {
        return orderService.createOrder(order);
    }
}
