package com.example.order_service.event;

import java.io.Serializable;

public class OrderCreatedEvent implements Serializable {

    private Long orderId;
    private Long productId;
    private String productName;
    private int quantity;
    private String status;

    // Default Constructor (VERY IMPORTANT for Kafka)
    public OrderCreatedEvent() {
    }
    // Parameterized Constructor
    public OrderCreatedEvent(Long orderId, Long productId, String productName, int quantity, String status) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString() for logging
    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
