package com.example.order_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "productId")
    private Long productId;
    // Default constructor
    public OrderEntity() {
    }

    // Parameterized constructor
    public OrderEntity(Long orderId, String productName, int quantity, String status,  Long productId) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
        this.productId = productId;
    }

    // Getter and Setter for orderId
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    // Getter and Setter for productName
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter and Setter for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}