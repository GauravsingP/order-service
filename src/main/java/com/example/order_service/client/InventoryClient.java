package com.example.order_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service" , fallback = InventoryClientFallback.class)
public interface InventoryClient {
    @GetMapping("inventory/check-stock/{productId}")
    String checkStock(@PathVariable String productId);
}
