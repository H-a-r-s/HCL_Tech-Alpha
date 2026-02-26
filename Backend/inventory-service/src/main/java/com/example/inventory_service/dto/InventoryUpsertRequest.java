package com.example.inventory_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class InventoryUpsertRequest {

    @NotNull(message = "productId is required")
    private Long productId;

    @NotNull(message = "stockQty is required")
    @Min(value = 0, message = "stockQty cannot be negative")
    private Integer stockQty;

    public InventoryUpsertRequest() {}

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getStockQty() { return stockQty; }
    public void setStockQty(Integer stockQty) { this.stockQty = stockQty; }
}