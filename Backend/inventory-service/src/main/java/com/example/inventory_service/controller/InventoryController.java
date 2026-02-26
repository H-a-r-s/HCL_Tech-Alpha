package com.example.inventory_service.controller;

import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public InventoryResponse get(@PathVariable Long productId) {
        return service.get(productId);
    }
}