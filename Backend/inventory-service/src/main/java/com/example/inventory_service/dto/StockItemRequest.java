package com.example.inventory_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StockItemRequest {
    @NotNull(message = "productId is required")
    private Long productId;

    @NotNull(message = "qty is required")
    @Min(value = 1, message = "qty must be >= 1")
    private Integer qty;

    public StockItemRequest() {}

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQty() { return qty; }
    public void setQty(Integer qty) { this.qty = qty; }
}