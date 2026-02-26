package com.example.order_service.dto;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockCheckRequest {
    private List<StockItemRequest> items;



    public List<StockItemRequest> getItems() { return items; }
    public void setItems(List<StockItemRequest> items) { this.items = items; }
}