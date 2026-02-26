package com.example.order_service.client;

import com.example.order_service.dto.StockCheckRequest;
import com.example.order_service.dto.StockCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventoryClient", url = "${inventory.service.url}")
public interface InventoryClient {

    @PostMapping("/api/internal/inventory/check")
    StockCheckResponse check(@RequestBody StockCheckRequest request);

    @PostMapping("/api/internal/inventory/decrease")
    void decrease(@RequestBody StockCheckRequest request);
}