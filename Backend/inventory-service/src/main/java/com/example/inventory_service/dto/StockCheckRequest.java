package com.example.inventory_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class StockCheckRequest {
    @NotEmpty(message = "items cannot be empty")
    @Valid
    private List<StockItemRequest> items;

    public StockCheckRequest() {}

    public List<StockItemRequest> getItems() { return items; }
    public void setItems(List<StockItemRequest> items) { this.items = items; }
}