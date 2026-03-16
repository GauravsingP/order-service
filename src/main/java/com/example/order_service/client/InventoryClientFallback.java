package com.example.order_service.client;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class InventoryClientFallback implements InventoryClient {
    @Override
    @GetMapping("check-stock/{productId}")
    public String checkStock(@PathVariable String productId){
        return "Inventory Service is currently unavailable. Please try later.";
    }
}
