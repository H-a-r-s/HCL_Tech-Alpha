package com.example.inventory_service.controller;

import com.example.inventory_service.dto.StockCheckRequest;
import com.example.inventory_service.dto.StockCheckResponse;
import com.example.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/inventory")
public class InventoryInternalController {

    private final InventoryService service;

    public InventoryInternalController(InventoryService service) {
        this.service = service;
    }

    @PostMapping("/check")
    public StockCheckResponse check(@Valid @RequestBody StockCheckRequest req) {
        return service.check(req);
    }

    @PostMapping("/decrease")
    public void decrease(@Valid @RequestBody StockCheckRequest req) {
        service.decreaseOrThrow(req);
    }
}