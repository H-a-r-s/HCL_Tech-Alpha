package com.example.inventory_service.dto;

import java.time.Instant;

public class InventoryResponse {
    private Long productId;
    private Integer stockQty;
    private Instant updatedAt;

    public InventoryResponse() {}

    public InventoryResponse(Long productId, Integer stockQty, Instant updatedAt) {
        this.productId = productId;
        this.stockQty = stockQty;
        this.updatedAt = updatedAt;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getStockQty() { return stockQty; }
    public void setStockQty(Integer stockQty) { this.stockQty = stockQty; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}