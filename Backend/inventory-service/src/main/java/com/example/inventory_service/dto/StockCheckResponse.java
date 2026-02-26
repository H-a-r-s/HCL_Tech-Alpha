package com.example.inventory_service.dto;

import java.util.List;

public class StockCheckResponse {
    private boolean allAvailable;
    private List<Long> outOfStockProductIds;

    public StockCheckResponse() {}

    public StockCheckResponse(boolean allAvailable, List<Long> outOfStockProductIds) {
        this.allAvailable = allAvailable;
        this.outOfStockProductIds = outOfStockProductIds;
    }

    public boolean isAllAvailable() { return allAvailable; }
    public void setAllAvailable(boolean allAvailable) { this.allAvailable = allAvailable; }

    public List<Long> getOutOfStockProductIds() { return outOfStockProductIds; }
    public void setOutOfStockProductIds(List<Long> outOfStockProductIds) { this.outOfStockProductIds = outOfStockProductIds; }
}