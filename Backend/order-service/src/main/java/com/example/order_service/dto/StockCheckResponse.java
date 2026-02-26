package com.example.order_service.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockCheckResponse {
    private boolean allAvailable;
    private List<Long> outOfStockProductIds;



    public boolean isAllAvailable() { return allAvailable; }
    public void setAllAvailable(boolean allAvailable) { this.allAvailable = allAvailable; }

    public List<Long> getOutOfStockProductIds() { return outOfStockProductIds; }
    public void setOutOfStockProductIds(List<Long> outOfStockProductIds) { this.outOfStockProductIds = outOfStockProductIds; }
}