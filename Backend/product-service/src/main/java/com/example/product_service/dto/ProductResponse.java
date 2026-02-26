package com.example.product_service.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;

    private Long brandId;
    private String brandName;

    private Long categoryId;
    private String categoryName;

    private String name;
    private String description;
    private double price;
    private boolean isActive;

}