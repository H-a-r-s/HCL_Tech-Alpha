package com.example.inventory_service.service;

import com.example.inventory_service.dto.*;
import com.example.inventory_service.entity.Inventory;
import com.example.inventory_service.exception.NotFoundException;
import com.example.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repo;

    public InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }

    public InventoryResponse get(Long productId) {
        Inventory inv = repo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Inventory not found for productId: " + productId));
        return toResponse(inv);
    }

    public InventoryResponse upsert(InventoryUpsertRequest req) {
        Inventory inv = repo.findById(req.getProductId()).orElseGet(() ->
                Inventory.builder()
                        .productId(req.getProductId())
                        .stockQty(0)
                        .updatedAt(Instant.now())
                        .build()
        );

        inv.setStockQty(req.getStockQty());
        inv.setUpdatedAt(Instant.now());
        return toResponse(repo.save(inv));
    }

    public InventoryResponse addStock(Long productId, int qty) {
        if (qty <= 0) throw new IllegalStateException("qty must be > 0");

        Inventory inv = repo.findById(productId).orElseGet(() ->
                Inventory.builder()
                        .productId(productId)
                        .stockQty(0)
                        .updatedAt(Instant.now())
                        .build()
        );

        inv.setStockQty(inv.getStockQty() + qty);
        inv.setUpdatedAt(Instant.now());
        return toResponse(repo.save(inv));
    }

    public StockCheckResponse check(StockCheckRequest req) {
        List<Long> out = new ArrayList<>();

        for (StockItemRequest item : req.getItems()) {
            Inventory inv = repo.findById(item.getProductId()).orElse(null);
            int available = (inv == null) ? 0 : inv.getStockQty();
            if (available < item.getQty()) {
                out.add(item.getProductId());
            }
        }

        return new StockCheckResponse(out.isEmpty(), out);
    }

    @Transactional
    public void decreaseOrThrow(StockCheckRequest req) {
        // Transaction ensures atomic update
        for (StockItemRequest item : req.getItems()) {
            Inventory inv = repo.findById(item.getProductId())
                    .orElseThrow(() -> new NotFoundException("Inventory not found for productId: " + item.getProductId()));

            if (inv.getStockQty() < item.getQty()) {
                throw new IllegalStateException("Insufficient stock for productId: " + item.getProductId());
            }

            inv.setStockQty(inv.getStockQty() - item.getQty());
            inv.setUpdatedAt(Instant.now());
            repo.save(inv);
        }
    }

    private InventoryResponse toResponse(Inventory inv) {
        return new InventoryResponse(inv.getProductId(), inv.getStockQty(), inv.getUpdatedAt());
    }
}