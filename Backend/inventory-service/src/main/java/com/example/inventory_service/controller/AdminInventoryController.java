package com.example.inventory_service.controller;

import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.dto.InventoryUpsertRequest;
import com.example.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/inventory")
public class AdminInventoryController {

    private final InventoryService service;

    public AdminInventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping
    public InventoryResponse upsert(@Valid @RequestBody InventoryUpsertRequest req) {
        return service.upsert(req);
    }

    @PatchMapping("/{productId}/add")
    public InventoryResponse add(@PathVariable Long productId, @RequestParam int qty) {
        return service.addStock(productId, qty);
    }

    @PutMapping("/{productId}")
    public InventoryResponse set(@PathVariable Long productId, @RequestParam int qty) {
        InventoryUpsertRequest req = new InventoryUpsertRequest();
        req.setProductId(productId);
        req.setStockQty(qty);
        return service.upsert(req);
    }
}