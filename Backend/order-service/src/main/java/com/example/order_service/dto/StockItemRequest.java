package com.example.order_service.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockItemRequest {
    private Long productId;
    private Integer qty;



    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQty() { return qty; }
    public void setQty(Integer qty) { this.qty = qty; }
}